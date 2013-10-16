package ee.ut.math.tvt.BSS;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.log4j.Logger;


public class IntroUI extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(IntroUI.class);	
	
	private JLabel TName;
	private JLabel TLeader;
	private JLabel TLEmail;
	private JLabel TMember;
	private JLabel TMember1;
	private JLabel TMember2;
	private JLabel TMember3;
	private JLabel TMember4;
	private ImageIcon Logo;
	private JLabel LogoLabel;
	private JLabel Version;
	
	private String appProp = "./application.properties";

	protected String getPropertyFromFile(String file_name, String key) throws IOException{
		String value = null;
		
		try {

			// to load application's properties, we use this class
			Properties mainProperties = new Properties();

			FileInputStream file;

			// load the file handle for main.properties
			file = new FileInputStream(file_name);						

			// load all the properties from this file
			mainProperties.load(new InputStreamReader(file, "UTF8"));

			// we have loaded the properties, so close the file handle
			file.close();

			// retrieve the property we are intrested, the "key"
			value = mainProperties.getProperty(key);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		return value;
	}
	protected String getAppVersion() throws IOException{
		String key = "build.number";
	    return getPropertyFromFile("./version.properties", key);
	}	
	
	public IntroUI(){
		super("IntroUI");
		log.info("starting IntroUI");
		try {
			setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.weighty = 1.0;
			c.weightx = 1.0;

			// BufferedReader br = new BufferedReader(new
			// FileReader("application.properties"));

			TName = new JLabel("Team name: "
					+ this.getPropertyFromFile(appProp, "Team name"));
			c.gridx = 0;
			c.gridy = 0;
			add(TName, c);

			TLeader = new JLabel("Team leader: "
					+ this.getPropertyFromFile(appProp, "Team leader"));
			c.gridx = 0;
			c.gridy = 1;
			add(TLeader, c);

			TLEmail = new JLabel("Team leader email: "
					+ this.getPropertyFromFile(appProp, "Team leader email"));
			c.gridx = 0;
			c.gridy = 2;
			add(TLEmail, c);

			TMember = new JLabel("Members");
			c.gridx = 0;
			c.gridy = 3;
			add(TMember, c);

			TMember1 = new JLabel(this.getPropertyFromFile(appProp, "member1"));
			c.gridx = 0;
			c.gridy = 4;
			add(TMember1, c);

			TMember2 = new JLabel(this.getPropertyFromFile(appProp, "member2"));
			c.gridx = 0;
			c.gridy = 5;
			add(TMember2, c);

			TMember3 = new JLabel(this.getPropertyFromFile(appProp, "member3"));
			c.gridx = 0;
			c.gridy = 6;
			add(TMember3, c);

			TMember4 = new JLabel(this.getPropertyFromFile(appProp, "member4"));
			c.gridx = 0;
			c.gridy = 7;
			add(TMember4, c);

			Version = new JLabel("Software version: " + this.getAppVersion());
			c.gridx = 0;
			c.gridy = 8;
			add(Version, c);

			Logo = new ImageIcon("logo.png");
			LogoLabel = new JLabel(Logo);
			c.gridx = 1;
			c.gridy = 0;
			c.gridheight = 9;
			add(LogoLabel, c);

			int width = 700;
			int height = 400;
			setSize(width, height);
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			setLocation((screen.width - width) / 2,
					(screen.height - height) / 2);
			setVisible(true);
			log.info("Intro window is opened");

		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			// br.close();
		}       
	}
	
	
}
