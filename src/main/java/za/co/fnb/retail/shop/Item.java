
package za.co.fnb.retail.shop;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Sales Item data structure.
 * 
 * @author Matsobane Khwinana 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private String name;
    private BigDecimal price;
    private TaxType taxType;

    public BigDecimal getTotalPrice() {
        BigDecimal tax = this.taxType.calculateTax(price);
        return tax.add(price);
    }
}
