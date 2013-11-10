package ee.ut.math.tvt.salessystem.domain.data;

import ee.ut.math.tvt.salessystem.util.HibernateUtil;

import org.hibernate.Session;

import java.util.Collections;
import java.util.List;


@SuppressWarnings("unchecked")
public class HibernateDataService {

	private Session session = HibernateUtil.currentSession();

	public List<StockItem> getStockItems() {		
		List<StockItem> result = session.createCriteria(StockItem.class).list();
		return result;
	}

}