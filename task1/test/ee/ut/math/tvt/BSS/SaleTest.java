package ee.ut.math.tvt.BSS;
 
import static org.junit.Assert.assertEquals;
 
import org.junit.Before;
import org.junit.Test;
 
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
 
public class SaleTest {
 
        PurchaseInfoTableModel model1 = new PurchaseInfoTableModel();
        PurchaseInfoTableModel model2 = new PurchaseInfoTableModel();
        PurchaseInfoTableModel model3 = new PurchaseInfoTableModel();
        PurchaseInfoTableModel model4 = new PurchaseInfoTableModel();
       
        SoldItem i1 = new SoldItem(new StockItem((long) 1, "testItem1", "desc1", 3.25), 2);
        SoldItem i2 = new SoldItem(new StockItem((long) 2, "testItem2", "desc2", 5.5), 3);
        SoldItem i3 = new SoldItem(new StockItem((long) 3, "testItem3", "desc3", 6.75), 2);
        SoldItem i4 = new SoldItem(new StockItem((long) 4, "testItem4", "desc4", 2), 4);
       
       
        @Before
        public void setUp() {
                model1.addItem(i1);
                model1.addItem(i2);
                model1.addItem(i3);
               
                model3.addItem(i1);
 
                model4.addItem(i1);
                model4.addItem(i2);
                model4.addItem(i3);
                model4.addItem(i4);
 
        }
       
       
       
        @Test
        public void testAddSoldItem(){
 
                double sum = i4.getSum();
                double totalsum = model1.getTotalAmount();
                model1.addItem(i4);
                assertEquals(model1.getTotalAmount(), totalsum + sum, 0.0001);
        }
       
        @Test
        public void testGetSumWithNoItems(){
 
                double sum = model2.getTotalAmount();
                assertEquals(sum, 0, 0.0001);
               
        }
       
        @Test
        public void testGetSumWithOneItems(){
               
                assertEquals(model3.getTotalAmount(), i1.getSum(), 0.0001);
 
               
        }
       
        @Test
        public void  testGetSumWithMultipleItems(){
 
                double sum = i1.getSum() + i2.getSum() + i3.getSum() + i4.getSum();
               
                assertEquals(sum, model4.getTotalAmount(), 0.0001);
        }
       
       
       
 
}