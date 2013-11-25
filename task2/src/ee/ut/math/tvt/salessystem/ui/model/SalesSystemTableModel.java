package ee.ut.math.tvt.salessystem.ui.model;

import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.table.AbstractTableModel;

import ee.ut.math.tvt.salessystem.domain.data.DisplayableItem;

/**
 * Generic table model implementation suitable for extending.
 */
public abstract class SalesSystemTableModel<T extends DisplayableItem> extends
        AbstractTableModel {

    private static final long serialVersionUID = 1L;

	// protected List<T> rows; // dzh 2013-11-25 from condition: need remove
    protected final String[] headers;

    public SalesSystemTableModel(final String[] headers) {
        this.headers = headers;
		// rows = new ArrayList<T>();
    }

    /**
     * @param item
     *            item describing selected row
     * @param columnIndex
     *            selected column index
     * @return value displayed in column with specified index
     */
    protected abstract Object getColumnValue(T item, int columnIndex);

    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public String getColumnName(final int columnIndex) {
        return headers[columnIndex];
    }

    public int getRowCount() {
		return this.getTableRows().size(); // dzh 2013-11-25 rows->this.getTableRows()
    }

    public Object getValueAt(final int rowIndex, final int columnIndex) {
		return getColumnValue(this.getTableRows().get(rowIndex), columnIndex); // dzh 2013-11-25 rows->this.getTableRows()
    }

    // search for item with the specified id
    public T getItemById(final long id) {
		for (final T item : this.getTableRows()) { // dzh 2013-11-25 rows->this.getTableRows()
            if (item.getId() == id)
                return item;
        }
        throw new NoSuchElementException();
    }

	public abstract List<T> getTableRows();

	public abstract void clear();

    public void populateWithData(final List<T> data) {
		this.getTableRows().clear(); // dzh 2013-11-25 rows->this.getTableRows()
		this.getTableRows().addAll(data); // dzh 2013-11-25 rows->this.getTableRows()
    }
    
    public void addRow(T row) {
		this.getTableRows().add(row); // dzh 2013-11-25 rows->this.getTableRows()
        fireTableDataChanged();
    }
    
    public T getRow(int index) {
		return this.getTableRows().get(index); // dzh 2013-11-25 rows->this.getTableRows()
    }
    
	public List<T> getRows() {// dzh 2013-11-25 leave as alias, may be need
		return this.getTableRows(); // dzh 2013-11-25 rows->this.getTableRows()
    }
    
}
