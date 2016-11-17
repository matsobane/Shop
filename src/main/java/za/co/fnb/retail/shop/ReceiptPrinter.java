package za.co.fnb.retail.shop;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Implements the receipt printing use case.
 *
 * @author Matsobane Khwinana
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptPrinter {

    private Basket basket;

    public void print(int output) {
        System.out.printf(HEADER_FORMAT,output);
        printLineItem();
        printSalesTaxes();
        printTotal();
        System.out.println(EMPTY);
    }

    private void printLineItem() {
        this.basket.getItems().keySet().forEach((item) -> {
            final Integer quatity = this.basket.getItems().get(item);
            final BigDecimal roundedTotalPrice = round(item.getTotalPrice(), RoundingMode.UP);
            System.out.printf(LINE_ITEM_FORMAT, quatity, item.getName(), roundedTotalPrice);
        });
    }

    private void printSalesTaxes() {
        System.out.printf(SALES_TAXES_FORMAT, round(this.basket.getSalesTaxes(), RoundingMode.UP));
    }

    private void printTotal() {
        System.out.printf(TOTAL_FORMAT, this.basket.getTotal());
    }

    public BigDecimal round(BigDecimal value, RoundingMode roundingMode) {
        if (NEAREST_FIVE_CENT.signum() == 0) {
            return value;
        } else {
            BigDecimal divided = value.divide(NEAREST_FIVE_CENT, 0, roundingMode);
            BigDecimal result = divided.multiply(NEAREST_FIVE_CENT);
            return result;
        }
    }

    private static final String EMPTY = "";
    private static final String TOTAL_FORMAT = "Total: %.2f\n";
    private static final String HEADER_FORMAT = "\nOutput %d:\n\n";
    private static final String LINE_ITEM_FORMAT = "%d %s: %.2f\n";
    private static final String SALES_TAXES_FORMAT = "Sales Taxes: %.2f\n";
    
    private static final BigDecimal NEAREST_FIVE_CENT = new BigDecimal(0.05);
}
