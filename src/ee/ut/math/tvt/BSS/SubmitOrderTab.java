package ee.ut.math.tvt.BSS;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.log4j.Logger;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.tabs.PurchaseTab;


public class SubmitOrderTab extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SalesSystemModel model;
	//private PurchaseItemPanel panel;
	private static final Logger log = Logger.getLogger(SubmitOrderTab.class);
	double totalPrice;

	
	  
	public JLabel totalLabel;
	public JLabel totalField;
	public JLabel cashLabel;
	public JLabel changeLabel;
	public JTextField changeField;
	public JNumericField cashField;
	public JButton Submit;
	public JButton Cancel;
	
	
	public SubmitOrderTab(SalesSystemModel model) {
		super("Confirmation");
		this.model = model;
		draw();
	}

	
	
	  public static double round(double value, int places){
		  if(places<0)throw new IllegalArgumentException();
		  
		  long factor = (long)Math.pow(10, places);
		  value = value*factor;
		  long tmp = Math.round(value);
		  return (double) tmp/factor;
	  }
	
	  
	  
	protected void draw(){
						
		int width = 250;
		int height = 250;

		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);
		GridBagConstraints c = new GridBagConstraints();
		
		totalPrice = 0;
		for(int i = 0;i < model.getCurrentPurchaseTableModel().getRowCount();i++){
			totalPrice = totalPrice + (double)model.getCurrentPurchaseTableModel().getValueAt(i, 4);
		}
		
		totalLabel = new JLabel("Total: ");
		c.gridx = 0;
		c.gridy = 0;
		this.add(totalLabel,c);
		
		totalField = new JLabel(totalPrice + "");
		c.gridx = 1;
		c.gridy = 0;
		this.add(totalField,c);
		
		cashLabel = new JLabel("Cash: ");
		c.gridx = 0;
		c.gridy = 1;
		this.add(cashLabel,c);
		
		changeField = new JTextField();
		c.gridx = 1;
		c.gridy = 2;					
		changeField.setEditable(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(changeField,c);
		
		

		
		cashField = new JNumericField();
		cashField.setMaxLength(6); //Set maximum length             
		cashField.setPrecision(2); //Set precision (1 in your case)              
		cashField.setAllowNegative(true); //Set false to disable negatives
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		cashField.setEditable(true);
		cashField.getDocument().addDocumentListener(new DocumentListener(){
			
			public void insertUpdate(DocumentEvent e) {
				warn();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				warn();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				warn();
			}
			
			public void warn(){
				try{
					if (!cashField.getText().isEmpty()){				
						double totalChange = Double.parseDouble(cashField.getText()) - totalPrice;
						totalChange = round(totalChange,2);
						changeField.setText(totalChange +"");
					}
					else {
						changeField.setText("");//dzh 2013-10-28 clear "return text" for example: after Ctrl+X 						
					}
				}
				catch(NumberFormatException e){
					
				}
				
			}
			
		});
		this.add(cashField,c);
		
		changeLabel = new JLabel("Return: ");
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		this.add(changeLabel,c);
		
		Submit = new JButton("Submit");
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.PAGE_END;
		Submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				SubmitButtonClicked();
				
				
			}
			
		});
		this.add(Submit,c);
		
		final JButton Cancel  = new JButton("Cancel");
		c.gridx = 1;
		c.gridy = 3;
		Cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CancelButtonClicked();
			}
			
		});
		this.add(Cancel,c);
		
		this.setSize(width, height);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - width) / 2,
				(screen.height - height) / 2);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		
		
		
		this.setVisible(true);
		
	
		
	}
	
	private void CancelButtonClicked() {
		this.dispose();
	}

	
	private void SubmitButtonClicked() {
		
		
		PurchaseTab.endSale();
		model.getCurrentPurchaseTableModel().clear();
		this.dispose();
		
		
		
	}
	

	

		
}