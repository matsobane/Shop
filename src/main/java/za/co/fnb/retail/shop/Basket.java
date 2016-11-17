package za.co.fnb.retail.shop;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.extern.java.Log;

/**
 * Implements the shopping Basket use cases
 * 
 * @author Matsobane Khwinana
 */
@Log
public class Basket {

    @Getter
    private final Map<Item, Integer> items = new HashMap<>();
    
    public void add(Item item, int i) {
        this.items.put(item, i);
    }

    public BigDecimal getTotal(){
        BigDecimal total = new BigDecimal(0);

        for(Item item:this.items.keySet()) {
            total = total.add(item.getTotalPrice());
        }
        
        return total;
    }

    public BigDecimal getSalesTaxes() {
        BigDecimal taxes = new BigDecimal(0);
        
        for(Item item: this.items.keySet()) {
            taxes = taxes.add(item.getTaxType().calculateTax(item.getPrice()));
        }
        
        return taxes;
    }

}
