package ee.ut.math.tvt.salessystem.ui.model;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.OrderHeader;

/**
 * History header table model.
 */

public class HistoryTableModel extends SalesSystemTableModel<OrderHeader> {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(StockTableModel.class);

	public HistoryTableModel() {
		super(new String[] {"Order id", "Date", "Time", "Sum"});
	}

	@Override
	protected Object getColumnValue(OrderHeader item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getDate();
		case 2:
			return item.getTime();
		case 3:
			return item.getSum();
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	
	public void addItem(final OrderHeader orderHeader) {
		rows.add(orderHeader);
		log.debug("Added " + orderHeader.getId()
					+ " summa of " + orderHeader.getSum());
		fireTableDataChanged();
	}

	
	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final OrderHeader orderHeader : rows) {
			buffer.append(orderHeader.getId() + "\t");
			buffer.append(orderHeader.getDate() + "\t");
			buffer.append(orderHeader.getTime() + "\t");
			buffer.append(orderHeader.getSum() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}	

	public Long genId() {
		Long result = Long.valueOf(0);
		for (OrderHeader orderHeader : rows)
		{
			if (Long.compare(result, orderHeader.getId()) < 0) {
				result = orderHeader.getId();
			}
		}
		return ++result;
	}
	
}	


