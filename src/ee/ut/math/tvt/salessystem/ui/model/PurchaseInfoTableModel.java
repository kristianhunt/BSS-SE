package ee.ut.math.tvt.salessystem.ui.model;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;

/**
 * Purchase history details model.
 */
public class PurchaseInfoTableModel extends SalesSystemTableModel<SoldItem> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(PurchaseInfoTableModel.class);
	
	public PurchaseInfoTableModel() {
		super(new String[] { "Id", "Name", "Price", "Quantity", "Sum"});
	}

	@Override
	protected Object getColumnValue(SoldItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getName();
		case 2:
			return item.getPrice();
		case 3:
			return item.getQuantity();
		case 4:
			return item.getSum();		
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final SoldItem item : rows) {
			buffer.append(item.getId() + "\t");
			buffer.append(item.getName() + "\t");
			buffer.append(item.getPrice() + "\t");
			buffer.append(item.getQuantity() + "\t");
			buffer.append(item.getSum() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}
	
	/**
	 * Get product quantity in Basket 
	 */
    public Integer getQuantityInBasketByBarCode(Long id) {
    	SoldItem row = null;
    	Integer quantityInBasket = 0;
    	try {
    		row = this.getItemById(id);
    		quantityInBasket = row.getQuantity();
    	} catch (NoSuchElementException e) {
    		// product not found in basket			
    	} catch (Exception e) {
    		log.error(e.getMessage());
    	}
    	return quantityInBasket;
    }	
	
    /**
     * Update quantity by bar code into table.
     */
    public void updateItemQuantity(Long id, Integer quantity) {
		SoldItem item = null;
		try {
			item = this.getItemById(id);
		} catch (NoSuchElementException e) {
			// product not found in basket
			log.error("product not found in basket: " + e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		if (item != null) {
			if (quantity + item.getQuantity() == 0) {
				rows.remove(item);
			} else {
				item.setQuantity(quantity + item.getQuantity());
				log.debug("Updated " + item.getName() + " new quantity is "
						+ item.getQuantity());
			}
			fireTableDataChanged();
		}
    }
    
    /**
     * Add new StockItem to table.
     */
    public void addItem(final SoldItem item) {
        /**
         * XXX In case such stockItem already exists increase the quantity of the
         * existing stock.
         */

		rows.add(item);
		log.debug("Added " + item.getName() + " quantity of "
				+ item.getQuantity());
		
        fireTableDataChanged();
    }
    
    public double getTotalAmount() { //dzh 2013-10-28 Total amount of purchase table
    	double result = 0;	
    	for (SoldItem item : this.getTableRows()) {
    		result += item.getSum();
    	}
    	return result; 	
    }
}
