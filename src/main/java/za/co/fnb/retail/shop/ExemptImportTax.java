package za.co.fnb.retail.shop;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Tax for exempt items that are imported.
 * 
 * @author Matsobane Khwinana
 */
public class ExemptImportTax extends TaxType{

    @Override
    public BigDecimal calculateTax(BigDecimal price) {
        return round(price.multiply(FIVE_PERCENT), RoundingMode.UP);
    }
    
    private static final BigDecimal FIVE_PERCENT = new BigDecimal(0.05);
}
