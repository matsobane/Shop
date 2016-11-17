package za.co.fnb.retail.shop;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.extern.java.Log;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Matsobane Khwinana (Matsobane.Khwinana@momentum.co.za)
 */
@Log
public class ShopTest {

    private ReceiptPrinter receiptPrinterOne;
    private ReceiptPrinter receiptPrinterTwo;
    private ReceiptPrinter receiptPrinterThree;

    @Before
    public void setUp() {
        this.receiptPrinterOne = new ReceiptPrinter(new Basket());
        this.receiptPrinterTwo = new ReceiptPrinter(new Basket());
        this.receiptPrinterThree = new ReceiptPrinter(new Basket());
    }

    @Test
    public void testInputOne() {
        //Given
        Item bookItem = new Item("book", new BigDecimal(12.49), new ExemptDomesticTax());
        Item musicCdItem = new Item("music CD", new BigDecimal(14.99), new DomesticTax());
        Item chocBarItem = new Item("chocolate bar", new BigDecimal(0.85), new ExemptDomesticTax());
        BigDecimal expectedSalesTaxes = round(new BigDecimal(1.50),RoundingMode.UP);

        //When
        this.receiptPrinterOne.getBasket().add(bookItem, 1);
        this.receiptPrinterOne.getBasket().add(musicCdItem, 1);
        this.receiptPrinterOne.getBasket().add(chocBarItem, 1);

        //Then
        final BigDecimal actualSalesTaxes = this.receiptPrinterOne.getBasket().getSalesTaxes();
        assertTrue(actualSalesTaxes.compareTo(expectedSalesTaxes) == 0);
        this.receiptPrinterOne.print(1);

    }

    @Test
    public void testInputTwo() {
        //Given
        Item importedChocolates = new Item("imported box of chocolates", new BigDecimal(10.00), new ExemptImportTax());
        Item importedPerfume = new Item("imported bottle of perfume", new BigDecimal(47.50), new ImportTax());
        BigDecimal expectedSalesTaxes = round(new BigDecimal(7.65),RoundingMode.UP);

        //When
        this.receiptPrinterTwo.getBasket().add(importedChocolates, 1);
        this.receiptPrinterTwo.getBasket().add(importedPerfume, 1);

        //Then
        final BigDecimal actualSalesTaxes = this.receiptPrinterTwo.getBasket().getSalesTaxes();
        assertEquals(actualSalesTaxes,expectedSalesTaxes);
        this.receiptPrinterTwo.print(2);

    }

    @Test
    public void testInputThree() {
        //Given
        Item importedPerfume = new Item("imported bottle of perfume", new BigDecimal(27.99), new ImportTax());
        Item perfume = new Item("bottle of perfume", new BigDecimal(18.99), new DomesticTax());
        Item pills = new Item("packet of headache pills", new BigDecimal(9.75), new ExemptDomesticTax());
        Item importedChocolates = new Item("imported box of chocolates", new BigDecimal(11.25), new ExemptImportTax());

        BigDecimal expectedSalesTaxes = round(new BigDecimal(6.70),RoundingMode.UP);

        //When
        this.receiptPrinterThree.getBasket().add(importedPerfume, 1);
        this.receiptPrinterThree.getBasket().add(perfume, 1);
        this.receiptPrinterThree.getBasket().add(pills, 1);
        this.receiptPrinterThree.getBasket().add(importedChocolates, 1);

        //Then
        this.receiptPrinterThree.print(3);
        final BigDecimal actualSalesTaxes = this.receiptPrinterThree.getBasket().getSalesTaxes();
        assertEquals(actualSalesTaxes,expectedSalesTaxes);

    }
    
    @After
    public void tearDown() {
        this.receiptPrinterOne = null;
        this.receiptPrinterTwo = null;
        this.receiptPrinterThree = null;
    }
    
    private BigDecimal round(BigDecimal value, RoundingMode roundingMode) {
        if (NEAREST_FIVE_CENT.signum() == 0) {
            return value;
        } else {
            BigDecimal divided = value.divide(NEAREST_FIVE_CENT, 0, roundingMode);
            BigDecimal result = divided.multiply(NEAREST_FIVE_CENT);
            return result;
        }
    }
    private final BigDecimal NEAREST_FIVE_CENT = new BigDecimal(0.05);
}
