package za.co.fnb.retail.shop;

import java.math.BigDecimal;
import lombok.Data;
import lombok.extern.java.Log;

/**
 * The main program for running the shop receipt printer.
 *
 * @author Matsobane Khwinana
 */
@Log
@Data
public class FnbShop {

    private static ReceiptPrinter printer;

    public static void main(String[] args) {
        
        printer = new ReceiptPrinter(inputOneBasket());
        printer.print(1);

        printer = new ReceiptPrinter(inputTwoBasket());
        printer.print(2);

        printer = new ReceiptPrinter(inputThreeBasket());
        printer.print(3);
      
    }

    private static Basket inputOneBasket() {

        Basket basket = new Basket();
        Item bookItem = new Item("book", new BigDecimal(12.49), new ExemptDomesticTax());
        Item musicCdItem = new Item("music CD", new BigDecimal(14.99), new DomesticTax());
        Item chocBarItem = new Item("chocolate bar", new BigDecimal(0.85), new ExemptDomesticTax());

        basket.add(bookItem, 1);
        basket.add(musicCdItem, 1);
        basket.add(chocBarItem, 1);

        return basket;
    }

    private static Basket inputTwoBasket() {
        Basket basket = new Basket();
        Item importedChocolates = new Item("imported box of chocolates", new BigDecimal(10.00), new ExemptImportTax());
        Item importedPerfume = new Item("imported bottle of perfume", new BigDecimal(47.50), new ImportTax());

        basket.add(importedChocolates, 1);
        basket.add(importedPerfume, 1);

        return basket;
    }

    private static Basket inputThreeBasket() {
        Basket basket = new Basket();
        Item importedPerfume = new Item("imported bottle of perfume", new BigDecimal(27.99), new ImportTax());
        Item perfume = new Item("bottle of perfume", new BigDecimal(18.99), new DomesticTax());
        Item pills = new Item("packet of headache pills", new BigDecimal(9.75), new ExemptDomesticTax());
        Item importedChocolates = new Item("imported box of chocolates", new BigDecimal(11.25), new ExemptImportTax());

        basket.add(importedPerfume, 1);
        basket.add(perfume, 1);
        basket.add(pills, 1);
        basket.add(importedChocolates, 1);

        return basket;
    }
}
