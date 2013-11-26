package ee.ut.math.tvt.BSS;
 
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.OrderHeader;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
 
public class SaleTest {
 
	private SoldItem i1;
	private SoldItem i2;
	private SoldItem i3;
	private SoldItem i4;

	@Before
	public void setUp() {
		i1 = new SoldItem(new StockItem((long) 1, "testItem1", "desc1", 3.25), 2);
		i2 = new SoldItem(new StockItem((long) 2, "testItem2", "desc2", 5.5), 3);
		i3 = new SoldItem(new StockItem((long) 3, "testItem3", "desc3", 6.75), 2);
		i4 = new SoldItem(new StockItem((long) 4, "testItem4", "desc4", 2), 4);
	}
       
	@Test
	public void testAddSoldItem() {
		OrderHeader order1 = new OrderHeader();
		order1.AddItem(i1);
		order1.AddItem(i2);
		order1.AddItem(i3);
		
		double sum = i4.getSum();
		double totalsum = order1.getSum();
		order1.AddItem(i4);

		assertEquals(order1.getSum(), totalsum + sum, 0.0001);
	}

	@Test
	public void testGetSumWithNoItems() {
		OrderHeader order2 = new OrderHeader();
		double sum = order2.getSum();
		assertEquals(sum, 0, 0.0001);
	}

	@Test
	public void testGetSumWithOneItems() {
		OrderHeader order3 = new OrderHeader();
		order3.AddItem(i1);

		assertEquals(order3.getSum(), i1.getSum(), 0.0001);
		assertEquals(order3.getSum(), i1.getPrice() * i1.getQuantity(), 0.0001);
	}

	@Test
	public void testGetSumWithMultipleItems() {
		OrderHeader order4 = new OrderHeader();
		order4.AddItem(i1);
		order4.AddItem(i2);
		order4.AddItem(i3);
		order4.AddItem(i4);

		double sum = i1.getSum() + i2.getSum() + i3.getSum() + i4.getSum();

		assertEquals(sum, order4.getSum(), 0.0001);
	}
       
 
}