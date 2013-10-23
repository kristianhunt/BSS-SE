package ee.ut.math.tvt.salessystem.ui.tabs;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class StockTab {

  private JButton addItem;

  private SalesSystemModel model;

  public StockTab(SalesSystemModel model) {
    this.model = model;
  }

  // warehouse stock tab - consists of a menu and a table
  public Component draw() {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    GridBagLayout gb = new GridBagLayout();
    GridBagConstraints gc = new GridBagConstraints();
    panel.setLayout(gb);

    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.anchor = GridBagConstraints.NORTH;
    gc.gridwidth = GridBagConstraints.REMAINDER;
    gc.weightx = 1.0d;
    gc.weighty = 0d;

    panel.add(drawStockMenuPane(), gc);

    gc.weighty = 1.0;
    gc.fill = GridBagConstraints.BOTH;
    panel.add(drawStockMainPane(), gc);
    return panel;
  }

  // warehouse menu
  private Component drawStockMenuPane() {
    JPanel panel = new JPanel();

    GridBagConstraints gc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();

    panel.setLayout(gb);

    gc.anchor = GridBagConstraints.NORTHWEST;
    gc.weightx = 0;

    addItem = new JButton("Add");
    
    addItem.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          submitAddItemButtonClicked();
        }
      });
    
    gc.gridwidth = GridBagConstraints.RELATIVE;
    gc.weightx = 1.0;
    panel.add(addItem, gc);

    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    return panel;
  }



private void submitAddItemButtonClicked (){
	EventQueue.invokeLater(new Runnable(){

	@Override
	public void run() {
		int width = 250;
		int height = 250;
		
		final JFrame frame = new JFrame("Add new item");
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints c = new  GridBagConstraints();
		frame.setLayout(gbl);
		
		JLabel id = new JLabel("ID: ");
		c.gridx = 0;
		c.gridy = 0;
		frame.add(id,c);
		
		JLabel name = new JLabel("Name: ");
		c.gridx = 0;
		c.gridy = 1;
		frame.add(name,c);
		
		JLabel price = new JLabel("Price: ");
		c.gridx = 0;
		c.gridy = 2;
		frame.add(price,c);
		
		JLabel  quantity= new JLabel("Quantity: ");
		c.gridx = 0;
		c.gridy = 3;
		frame.add(quantity,c);
		
		JLabel description = new JLabel("Description: ");
		c.gridx = 0;
		c.gridy = 4;
		frame.add(description,c);
		
		final JTextField idField = new JTextField();
		c.gridx = 1;
		c.gridy = 0;
		idField.setEditable(true);
		c.fill = GridBagConstraints.HORIZONTAL;
		Long newBarcode = new Long(0);
		java.util.List<StockItem> getRows = model.getWarehouseTableModel().getTableRows();

		for (int i = 0; i < model.getWarehouseTableModel().getRowCount(); i++) {
			 if (newBarcode < getRows.get(i).getId()) newBarcode = getRows.get(i).getId();
		}
		newBarcode ++;
		idField.setText(newBarcode.toString());
		frame.add(idField,c);
		
		
		final JTextField nameField = new JTextField();
		c.gridx = 1;
		c.gridy = 1;
		nameField.setEditable(true);
		c.fill = GridBagConstraints.HORIZONTAL;
		frame.add(nameField,c);
		
		final JTextField priceField = new JTextField();
		c.gridx = 1;
		c.gridy = 2;
		nameField.setEditable(true);
		c.fill = GridBagConstraints.HORIZONTAL;
		frame.add(priceField,c);
		
		final JTextField quantityField = new JTextField();
		c.gridx = 1;
		c.gridy = 3;
		nameField.setEditable(true);
		c.fill = GridBagConstraints.HORIZONTAL;
		frame.add(quantityField,c);
		
		final JTextField descriptionField = new JTextField();
		c.gridx = 1;
		c.gridy = 4;
		descriptionField.setEditable(true);
		c.fill = GridBagConstraints.HORIZONTAL;
		frame.add(descriptionField,c);
		
		JButton Submit = new JButton("Submit");
		c.gridx = 1;
		c.gridy = 5;
		c.anchor = GridBagConstraints.PAGE_END;
		Submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isComplete = true;
				long itemID = 0;
				String itemName = null;
				String itemDesc = null;
				double itemPrice = 0;
				int itemQuantity = 0;
				try{
					if(idField.getText().isEmpty() || nameField.getText().isEmpty()
					|| priceField.getText().isEmpty() || quantityField.getText().isEmpty()){				
						isComplete = false;
					}
					
					itemID = Long.parseLong(idField.getText());
					itemName = nameField.getText();
					itemDesc = descriptionField.getText();
					itemPrice = Double.parseDouble(priceField.getText());
					itemQuantity = Integer.parseInt(quantityField.getText());
					
					}
					catch(NumberFormatException e1){
						isComplete = false;
					}
				if (isComplete) {
					StockItem newStock = new StockItem(itemID, itemName,itemDesc, itemPrice, itemQuantity);
					System.out.println(newStock.toString());
					model.getWarehouseTableModel().addItem(newStock);
					frame.dispose();
				}
			}
		});
		frame.add(Submit,c);
		
		JButton Cancel = new JButton("Cancel");
		c.gridx = 0;
		c.gridy = 5;
		c.anchor = GridBagConstraints.PAGE_END;
		Cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		frame.add(Cancel,c);
		
		frame.setSize(width, height);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screen.width - width) / 2,
				(screen.height - height) / 2);
	    c.fill = GridBagConstraints.BOTH;
	    c.weightx = 1.0d;
	    c.weighty = 0d;
	    c.fill = GridBagConstraints.BOTH;
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		frame.setVisible(true);
		}
	});
}

  
// table of the wareshouse stock
  private Component drawStockMainPane() {
    JPanel panel = new JPanel();

    JTable table = new JTable(model.getWarehouseTableModel());

    JTableHeader header = table.getTableHeader();
    header.setReorderingAllowed(false);

    JScrollPane scrollPane = new JScrollPane(table);

    GridBagConstraints gc = new GridBagConstraints();
    GridBagLayout gb = new GridBagLayout();
    gc.fill = GridBagConstraints.BOTH;
    gc.weightx = 1.0;
    gc.weighty = 1.0;

    panel.setLayout(gb);
    panel.add(scrollPane, gc);

    panel.setBorder(BorderFactory.createTitledBorder("Warehouse status"));
    return panel;
  }

}
