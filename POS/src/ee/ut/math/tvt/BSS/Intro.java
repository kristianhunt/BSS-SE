package ee.ut.math.tvt.BSS;

import javax.swing.JFrame;
import org.apache.log4j.Logger;

public class Intro {
	
	private static final Logger log = Logger.getLogger(Intro.class);	
	
	public static void main(String[] args) {
		log.info("Start application");
		IntroUI a = new IntroUI();
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
