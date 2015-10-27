package idv;

import idv.domain.InvoiceWinningNumbersDeclaration;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UniformInvoicePrizeUtils

{
    private static final Logger LOGGER = LoggerFactory.getLogger(UniformInvoicePrizeUtils.class);

    private int getYear(String title) {
        return Integer.parseInt(title.substring(0, 3)) + 1911;
    }

    private Date getStartMonth(String title, int year) {
        DateTime dt = new DateTime();
        dt.withYear(year);
        dt.withMonthOfYear(Integer.parseInt(title.substring(4, 6)));
        dt.withDayOfMonth(01);
        dt.withTime(0, 0, 0, 0);
        return dt.toDate();
    }

    private Date getEndMonth(String title, int year) {
        DateTime dt = new DateTime();
        dt.withYear(year);
        dt.withMonthOfYear(Integer.parseInt(title.substring(7, 9)));
        dt = dt.dayOfMonth().withMaximumValue();
        dt.withTime(23, 59, 59, 59);
        return dt.toDate();
    }

    private String getTitle(Document doc) {
        Element h2 = doc.select("#area1 h2:nth-child(2)").first();
        return h2.text();
    }

    private InvoiceWinningNumbersDeclaration createInvoiceWinningNumbersDeclaration(Document doc) {
        String title = getTitle(doc);
        int year = getYear(title);
        Date startMonth = getStartMonth(title, year);
        Date endMonth = getEndMonth(title, year);
        return new InvoiceWinningNumbersDeclaration(startMonth, endMonth);
    }

    private void setInvoiceWinningNumbersDeclaration(InvoiceWinningNumbersDeclaration declaration, Iterator<Element> tds) {
        int cnt = 0;
        List<String> firstPrizes = declaration.getFirstPrizes();

        while (tds.hasNext()) {
            cnt++;
            String val = tds.next().text();

            switch (cnt) {
                case 2:
                    setPrizesData(declaration.getSpecialPrizes(), val);
                    break;
                case 4:
                    setPrizesData(declaration.getGrandPrizes(), val);
                    break;
                case 6:
                    setPrizesData(firstPrizes, val);

                    for (String prize : firstPrizes) {
                        declaration.getAddSixPrizes().add(prize.substring(5));
                    }
                    break;
                case 18:
                    setPrizesData(declaration.getAddSixPrizes(), val);
                    break;
                default:
                    break;
            }
            declaration.setFirstPrizes(firstPrizes);
        }
    }

    public InvoiceWinningNumbersDeclaration getInvoiceWinningNumbersDeclaration() {

        InvoiceWinningNumbersDeclaration declaration = null;
        try {
            URL url = new URL("http://invoice.etax.nat.gov.tw/");

            // Create the Document Object
            Document doc = Jsoup.parse(url, 3000);
            declaration = createInvoiceWinningNumbersDeclaration(doc);
            // Get first table
            Element table = doc.select("table").first();
            // Get td Iterator
            Iterator<Element> tds = table.select("td").iterator();
            setInvoiceWinningNumbersDeclaration(declaration, tds);

        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        LOGGER.info(declaration.toString());

        return declaration;
    }

    /**
     * 設定中獎資料
     */
    private void setPrizesData(List<String> Prizes, String val) {
        if (val.split(" ")[0].split("、").length > 1) {
            Collections.addAll(Prizes, val.split(" ")[0].split("、"));
        } else {
            Collections.addAll(Prizes, val.split(" ")[0]);
        }
    }
}