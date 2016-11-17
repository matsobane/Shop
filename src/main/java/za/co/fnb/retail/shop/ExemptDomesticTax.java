package za.co.fnb.retail.shop;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Define  exempt local tax.
 * 
 * @author Matsobane Khwinana 
 */
public class ExemptDomesticTax extends TaxType{

    @Override
    public BigDecimal calculateTax(BigDecimal price) {
        return round(price.multiply(ZERO), RoundingMode.UP);
    }
    
    private static final BigDecimal ZERO = new BigDecimal(0.0);
    
}
