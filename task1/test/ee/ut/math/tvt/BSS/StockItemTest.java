package ee.ut.math.tvt.BSS;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockItemTest {
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
	public void testClone() {
		StockItem item2 = (StockItem) item1.clone();
		
		assertEquals(item2.getId().longValue(), item_id.longValue(), 0.0001);
	    assertEquals(item2.getName(), item_name);
	    assertEquals(item2.getPrice(), item_price, 0.001);
	    assertEquals(item2.getQuantity(), item_quantity);
	    assertEquals(item2.getDescription(), item_desc);
	}
	
	@Test
	public void testGetColumn() {
		Long return_id = (Long) item1.getColumn(0);
	    assertEquals(return_id.longValue(), item_id.longValue(), 0.0001);
	    
	    String return_name = (String) item1.getColumn(1);
	    assertEquals(return_name, item_name);
	    
		double return_price = ((Number) (Object) item1.getColumn(2)).doubleValue();
		assertEquals(return_price, item_price, 0.0001);
		
		int return_quantity = ((Integer) item1.getColumn(3)).intValue();
	    assertEquals(return_quantity, item_quantity, 0.0001);
	}

	@Test (expected = RuntimeException.class) 
	public void testGetColumnException() {
		item1.getColumn(4);
	}

}
