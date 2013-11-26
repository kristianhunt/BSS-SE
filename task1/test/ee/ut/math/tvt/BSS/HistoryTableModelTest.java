package ee.ut.math.tvt.BSS;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.OrderHeader;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.HistoryTableModel;

public class HistoryTableModelTest {

	private HistoryTableModel model;
	private OrderHeader orderHeader;
	private Set<SoldItem> solditems;

	private SoldItem soldItem1;
	private SoldItem soldItem2;

	private StockItem item1;
	private StockItem item2;

	private Long item_id = new Long(10);
	private String item_name = "testItem";
	private String item_desc = "testDescription";
	private double item_price = 12.0;
	private int item_quantity = 100;
	private int item_sell_quantity = 5;


	@Before
	public void setUp() {
		model = new HistoryTableModel();
		item1 = new StockItem(item_id, item_name, item_desc, item_price, item_quantity);
		item2 = new StockItem(item_id+1, item_name+"2", item_desc+"2", item_price+2, item_quantity+2);
		soldItem1 = new SoldItem(item1, item_sell_quantity);
		soldItem2 = new SoldItem(item2, item_sell_quantity);
	}

	@Test
	public void testAddItem(){
  	    solditems = new HashSet<SoldItem>();
		solditems.add(soldItem1);

  	    Date date = new Date();
		Time time = new Time(date.getTime());
		Long id = model.genId();		 
		orderHeader = new OrderHeader(id, date, time, 0, solditems);
		double sum = orderHeader.getSum();
		model.addItem(orderHeader);
		
		assertEquals(date, model.getItemById(id).getDate());
		assertEquals(time, model.getItemById(id).getTime());
		assertEquals(sum, model.getItemById(id).getSum(), 0.0001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testgetColumnValueByIllegalId(){
		orderHeader = new OrderHeader();
		model.getColumnValue(orderHeader, model.getColumnCount() + 100);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testgetColumnValueByIdMinusOne() {
		orderHeader = new OrderHeader();
		model.getColumnValue(orderHeader, -1);
	}

	@Test
	public void testHistoryOrderDetailCount() {
		solditems = new HashSet<SoldItem>();
		solditems.add(soldItem1);
		solditems.add(soldItem2);

		Date date = new Date();
		Time time = new Time(date.getTime());
		Long id = model.genId();
		orderHeader = new OrderHeader(id, date, time, 0, solditems);
		model.addItem(orderHeader);

		assertEquals(model.getItemById(id).getOrderDetail().size(), 2);

	}

}
