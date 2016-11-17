
package za.co.fnb.retail.shop;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.extern.java.Log;

/**
 * Non-exempt and imported.
 * 
 * @author Matsobane Khwinana
 */
@Log
public class ImportTax extends TaxType{

    @Override
    public BigDecimal calculateTax(BigDecimal price) {
        final BigDecimal tax = price.multiply(FIFTEEN_PERCENT);
        return round(tax, RoundingMode.UP);
    }
    
    private static final BigDecimal FIFTEEN_PERCENT = new BigDecimal(0.15);
    
}
