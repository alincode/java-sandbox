package idv;

import idv.domain.InvoiceWinningNumbersDeclaration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UniformInvoicePrizeUtilsTest {

    private UniformInvoicePrizeUtils uniformInvoicePrizeUtils;

    @Before
    public void setUp() throws Exception {
        uniformInvoicePrizeUtils = new UniformInvoicePrizeUtils();
    }

    @Test
    public void testGetInvoiceWinningNumbersDeclaration() throws Exception {
        InvoiceWinningNumbersDeclaration declaration = uniformInvoicePrizeUtils.getInvoiceWinningNumbersDeclaration();
        Assert.assertEquals(1, declaration.getSpecialPrizes().size());
        Assert.assertEquals(1, declaration.getGrandPrizes().size());
        Assert.assertEquals(3, declaration.getFirstPrizes().size());
        Assert.assertEquals(true, declaration.getAddSixPrizes().size() >= 3);
    }

}