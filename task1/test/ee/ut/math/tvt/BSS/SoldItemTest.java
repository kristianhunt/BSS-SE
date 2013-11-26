package ee.ut.math.tvt.BSS;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class SoldItemTest {
	private StockItem item1;
	
	private Long item_id = new Long(10);
	private String item_name = "testItem";
	private String item_desc = "testDescription";
	private double item_price = 12.0;
	private int item_quantity = 5;
		
	
	@Before
	public void setUp() {		
		item1 = new StockItem(item_id, item_name, item_desc, item_price, item_quantity);
	}
	
	@Test
	public void testGetSum() {
		int quantity = 3;
		SoldItem item2 = new SoldItem (item1, quantity);
		assertEquals(item2.getSum(), item_price * quantity, 0.001);
	}

	@Test
	public void testGetSumWithZeroQuantity() {
		int quantity = 0;
		SoldItem item3 = new SoldItem(item1, quantity);
		assertEquals(item3.getSum(), item_price * quantity, 0.001);
	}
}
