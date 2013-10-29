package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab {

	private static final Logger log = Logger.getLogger(HistoryTab.class);
	
    // TODO - implement!
	private SalesSystemModel model;
	protected JTable tableOrderDetail;
	protected JTable tableOrderHeader;
	
    public HistoryTab(SalesSystemModel model) {
    	this.model = model;
    } 
    
    public Component draw() {
        JPanel panel = new JPanel();
        // TODO - Sales history table        
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gc = new GridBagConstraints();
        panel.setLayout(gb);

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.NORTH;
        gc.gridwidth = GridBagConstraints.REMAINDER;        
        gc.weightx = 1.0d;
        gc.weighty = 0.3d;
        gc.fill = GridBagConstraints.BOTH;
        panel.add(drawHistoryMainPane(), gc);

        gc.weighty = 1.0;
        gc.fill = GridBagConstraints.BOTH;
        panel.add(drawHistoryDetailPane(), gc);
        return panel;
    }
    
 // table of the purchase history
    private Component drawHistoryMainPane() {
        JPanel panel = new JPanel();

        tableOrderHeader = new JTable(model.getHistoryTableModel());
        tableOrderHeader.addMouseListener(new java.awt.event.MouseAdapter() {
    	    @Override
    	    public void mouseClicked(java.awt.event.MouseEvent evt) {
    	        int row = tableOrderHeader.rowAtPoint(evt.getPoint());
    	        int col = tableOrderHeader.columnAtPoint(evt.getPoint());
    	        if (row >= 0 && col >= 0) {
    	        	List <SoldItem> a = (List <SoldItem>) model.getHistoryTableModel().getValueAt(row, 4);
    	        	PurchaseInfoTableModel currentOrderTableModel = new PurchaseInfoTableModel();
    	        	currentOrderTableModel.populateWithData(a);    	            
    	        	tableOrderDetail.setModel(currentOrderTableModel);
    	        	log.info("Items count: " + a.size());
    	        	log.info(tableOrderHeader.getValueAt(row, 0).toString());
    	        }
    	    }
    	});

        JTableHeader header = tableOrderHeader.getTableHeader();
        header.setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(tableOrderHeader);


        GridBagConstraints gc = new GridBagConstraints();
        GridBagLayout gb = new GridBagLayout();
        panel.setLayout(gb);

        gc.anchor = GridBagConstraints.NORTHWEST;
        gc.weightx = 0;

              
        //gc.gridwidth = GridBagConstraints.RELATIVE;
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;
        gc.weighty = 1.0;         
        panel.add(scrollPane, gc);

        //panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setBorder(BorderFactory.createTitledBorder("Purchase history"));
        return panel;    	   
    }

    // table of the purchase order detail
    private Component drawHistoryDetailPane() {
      JPanel panel = new JPanel();

      tableOrderDetail = new JTable();//model.getHistoryTableModel()
      tableOrderDetail.setName("tableOrderDetail");
      
      JTableHeader header = tableOrderDetail.getTableHeader();
      header.setReorderingAllowed(false);

      JScrollPane scrollPane = new JScrollPane(tableOrderDetail);

      GridBagConstraints gc = new GridBagConstraints();
      GridBagLayout gb = new GridBagLayout();
      gc.fill = GridBagConstraints.BOTH;
      gc.weightx = 1.0;
      gc.weighty = 1.0;

      panel.setLayout(gb);
      panel.add(scrollPane, gc);

      panel.setBorder(BorderFactory.createTitledBorder("Purchase detail"));
      return panel;
    }
    
    
}