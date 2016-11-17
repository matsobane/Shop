package za.co.fnb.retail.shop;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.extern.java.Log;

/**
 * Represents the basic tax type
 * 
 * @author Matsobane Khwinana
 */
@Log
public class DomesticTax extends TaxType{

    @Override
    public BigDecimal calculateTax(BigDecimal price) {
        final BigDecimal tax = price.multiply(TEN_PERCENT);
        return round(tax,RoundingMode.UP);
    }
    
    private static final BigDecimal TEN_PERCENT = new BigDecimal(0.10);
}
