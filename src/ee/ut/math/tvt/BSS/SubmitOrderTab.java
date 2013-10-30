package ee.ut.math.tvt.BSS;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.log4j.Logger;


public class SubmitOrderTab extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private SalesSystemModel model;
	//private PurchaseItemPanel panel;
	private static final Logger log = Logger.getLogger(SubmitOrderTab.class);
	double totalPrice;
	
	public Boolean ModalResult = false; 
	public JLabel totalLabel;
	public JLabel totalField;
	public JLabel cashLabel;
	public JLabel changeLabel;
	public JTextField changeField;
	public JNumericField cashField;
	public JButton Submit;
	public JButton Cancel;
	private double totalAmount;
	
	
	public SubmitOrderTab(/*SalesSystemModel model,*/ double a) {
		this(null, "Confirmation");			
		//this.model = model;
		this.totalAmount = a;
		draw();
	}

	public SubmitOrderTab(JFrame parent, String title) {
		super(parent, title, true);		
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
		

		
		totalLabel = new JLabel("Total: ");
		c.gridx = 0;
		c.gridy = 0;
		this.add(totalLabel,c);
		
		totalField = new JLabel(totalAmount + "");
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
						double totalChange = Double.parseDouble(cashField.getText()) - totalAmount;
						if(totalChange >= 0){
							Submit.setEnabled(true);
						}
						else{
							Submit.setEnabled(false);
						}
						totalChange = round(totalChange,2);
						changeField.setText(totalChange +"");
					}
					else {
						changeField.setText("");//dzh 2013-10-28 clear "return text" for example: after Ctrl+X 
						Submit.setEnabled(false);
					}
				}
				catch(NumberFormatException e){
					
				}
				finally{
					
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
		Submit.setEnabled(false);
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
		
	}

	private void CancelButtonClicked() {
		log.info("Payment - cancel");
		this.dispose();
	}
	
	private void SubmitButtonClicked() {
		this.ModalResult = true;
		log.info("Money ok");
		this.dispose();	
	}
	

	

		
}
