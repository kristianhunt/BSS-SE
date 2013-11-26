package ee.ut.math.tvt.BSS;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

public class StockTableModelTest {
	
	StockTableModel model1 = new StockTableModel();
	StockItem item1;
	Long item_id = new Long(10);
	int item_quantity = 5;
	String item_name = "testItem";
	
	@Before
	public void setUp() {		
		item1 = new StockItem(item_id, item_name, "testDescription", 12.0, item_quantity);
		model1.addItem(item1);
	}

	@Test
	public void testValidateIdUniqueness() { 
		/*
		 *  testValidateNameUniqueness() -> testValidateIdUniqueness()
		 *  because only checking ID 
		 */
		int add_quantity_in_stock = 1;
		int old_quantity_in_stock = model1.getItemById(item_id).getQuantity();		
		
		StockItem item2 = new StockItem (item_id, item_name, "testDescription 2", 10.0, add_quantity_in_stock);
		model1.addItem(item2);
		int new_quantity_in_stock = model1.getItemById(item_id).getQuantity();

		assertEquals(new_quantity_in_stock, old_quantity_in_stock + add_quantity_in_stock);
	}
	
	@Test
	public void testHasEnoughInStock() {
		/*
		 * (expected = IllegalArgumentException.class) - test not completed
		 */
		model1.addQuantity((long) 10, -100);

	}
	
	@Test
	public void testGetItemByIdWhenItemExists() {
		assertEquals(model1.getItemById((long) 10), item1);
	}
	
	@Test (expected = java.util.NoSuchElementException.class)
	public void testGetItemByIdWhenThrowsException() {
		model1.getItemById((long) 20);
	}

}
