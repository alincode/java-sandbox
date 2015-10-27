package idv.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceWinningNumbersDeclaration {

    public InvoiceWinningNumbersDeclaration(Date startMonth, Date endMonth) {
        this.startMonth = startMonth;
        this.endMonth = endMonth;
    }

    private Date startMonth;

    private Date endMonth;

    // 特別獎
    private List<String> specialPrizes = new ArrayList<String>();

    // 特獎
    private List<String> grandPrizes = new ArrayList<String>();

    // 頭獎
    private List<String> firstPrizes = new ArrayList<String>();

    // 增開六獎
    private List<String> addSixPrizes = new ArrayList<String>();

    public List<String> getSpecialPrizes() {
        return specialPrizes;
    }

    public List<String> getGrandPrizes() {
        return grandPrizes;
    }

    public List<String> getAddSixPrizes() {
        return addSixPrizes;
    }

    public void setFirstPrizes(List<String> firstPrizes) {
        this.firstPrizes = firstPrizes;
    }

    public List<String> getFirstPrizes() {
        return firstPrizes;
    }

    @Override
    public String toString() {
        return "InvoiceWinningNumbersDeclaration{" +
                "startMonth=" + startMonth +
                ", endMonth=" + endMonth +
                ", specialPrizes=" + specialPrizes +
                ", grandPrizes=" + grandPrizes +
                ", firstPrizes=" + firstPrizes +
                ", addSixPrizes=" + addSixPrizes +
                '}';
    }
}
