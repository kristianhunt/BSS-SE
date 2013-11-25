package ee.ut.math.tvt.BSS;
 
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.OrderHeader;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
 
public class SaleTest {
 
	OrderHeader order1 = new OrderHeader();
	OrderHeader order2 = new OrderHeader();
	OrderHeader order3 = new OrderHeader();
	OrderHeader order4 = new OrderHeader();
       
        SoldItem i1 = new SoldItem(new StockItem((long) 1, "testItem1", "desc1", 3.25), 2);
        SoldItem i2 = new SoldItem(new StockItem((long) 2, "testItem2", "desc2", 5.5), 3);
        SoldItem i3 = new SoldItem(new StockItem((long) 3, "testItem3", "desc3", 6.75), 2);
        SoldItem i4 = new SoldItem(new StockItem((long) 4, "testItem4", "desc4", 2), 4);
       
       
        @Before
        public void setUp() {
		order1.AddItem(i1);
		order1.AddItem(i2);
		order1.AddItem(i3);
               
		order3.AddItem(i1);
 
		order4.AddItem(i1);
		order4.AddItem(i2);
		order4.AddItem(i3);
		order4.AddItem(i4);
 
        }
       
       
       
        @Test
        public void testAddSoldItem(){
 
                double sum = i4.getSum();
		double totalsum = order1.getSum();
		order1.AddItem(i4);
		assertEquals(order1.getSum(), totalsum + sum, 0.0001);
        }
       
        @Test
        public void testGetSumWithNoItems(){
 
		double sum = order2.getSum();
                assertEquals(sum, 0, 0.0001);
               
        }
       
        @Test
        public void testGetSumWithOneItems(){
               
		assertEquals(order3.getSum(), i1.getSum(), 0.0001);
 
               
        }
       
        @Test
        public void  testGetSumWithMultipleItems(){
 
                double sum = i1.getSum() + i2.getSum() + i3.getSum() + i4.getSum();
               
		assertEquals(sum, order4.getSum(), 0.0001);
        }
       
       
       
 
}