package ee.ut.math.tvt.salessystem.domain.data;

import java.util.List;

import org.hibernate.Session;

import ee.ut.math.tvt.salessystem.util.HibernateUtil;


@SuppressWarnings("unchecked")
public class HibernateDataService {

	private Session session = HibernateUtil.currentSession();

	public List<StockItem> getStockItems() {		
		List<StockItem> result = session.createCriteria(StockItem.class).list();
		return result;
	}

	public List<OrderHeader> getOrders() {
		List<OrderHeader> result = session.createCriteria(OrderHeader.class)
				.list();
		return result;
	}

}