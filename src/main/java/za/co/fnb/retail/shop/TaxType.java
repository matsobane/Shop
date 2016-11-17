
package za.co.fnb.retail.shop;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Abstract tax type
 * 
 * @author Matsobane Khwinana
 */
public abstract class TaxType {
    public abstract BigDecimal calculateTax(BigDecimal price);
    
    public BigDecimal round(BigDecimal value, RoundingMode roundingMode) {
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
