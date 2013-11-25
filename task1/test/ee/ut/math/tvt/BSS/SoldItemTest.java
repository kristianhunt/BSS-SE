package ee.ut.math.tvt.BSS;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class SoldItemTest {
	private StockItem item1;
	
	@Before
	public void setUp() {
		long id = 10;
		item1 = new StockItem(id, "testItem", "testDescription", 12.0, 5);

	}
	
	@Test
	public void testGetSum() {
		SoldItem item2 = new SoldItem (item1, 3);
		assertEquals(item2.getSum(),36.0, 0.001);
	}

	@Test
	public void testGetSumWithZeroQuantity() {
		SoldItem item3 = new SoldItem (item1, 0);
		assertEquals(item3.getSum(), 0, 0.001);
	}
}
