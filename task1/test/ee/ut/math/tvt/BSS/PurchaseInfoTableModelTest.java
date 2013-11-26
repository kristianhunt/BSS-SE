package ee.ut.math.tvt.BSS;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;

public class PurchaseInfoTableModelTest {

	private PurchaseInfoTableModel model = new PurchaseInfoTableModel();

	private SoldItem item;

	private Long item_id = new Long(1);
	private String item_name = "testItem1";
	private String item_desc = "desc1";
	private double item_price = 3;
	private int item_quantity = 35;
	private int sold_quantity = 2;

	@Before
	public void setUp() {
		item = new SoldItem(new StockItem(item_id, item_name, item_desc,
			item_price, item_quantity), sold_quantity);
	}

	@Test
	public void testGetColumnValue() {
		Long return_id = (Long) model.getColumnValue(item, 0);
		assertEquals(return_id.longValue(), item_id.longValue(), 0.0001);

		String return_name = (String) model.getColumnValue(item, 1);
		assertEquals(return_name, item_name);

		double return_price = ((Number) (Object) model.getColumnValue(item, 2)).doubleValue();
		assertEquals(return_price, item_price, 0.001);

		int return_quantity = ((Integer) model.getColumnValue(item, 3)).intValue();		
		assertEquals(return_quantity, sold_quantity);

		double return_sum = ((Number) (Object) model.getColumnValue(item, 4)).doubleValue();
		assertEquals(return_sum, sold_quantity * item_price, 0.0001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetColumnByIllegalId() {
		model.addItem(item);
		model.getColumnValue(item, 5);
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void testInPurchaseNoSuchElementException() {
		model.getItemById((long) 20);
	}


}