package ee.ut.math.tvt.BSS;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

public class StockTableModelTest {
	
	StockTableModel model1 = new StockTableModel();
	StockItem item1;
	
	@Before
	public void setUp() {
		long id = 10;
		item1 = new StockItem(id, "testItem", "testDescription", 12.0, 5);
		model1.addItem(item1);
	}
	
	@Test (expected = Exception.class)
	public void testValidateNameUniqueness() {
		StockItem item2 = new StockItem ((long) 8, "testItem", "testDescription 2", 10.0, 2);
		model1.addItem(item2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testHasEnoughInStock() {
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
