package ee.ut.math.tvt.salessystem.domain.exception;

public class SalesSystemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// dzh 2013-11-26 default constructor
	public SalesSystemException() {
	}

	// dzh 2013-11-26 Constructor that accepts a message
	public SalesSystemException(String message) {
		super(message);
	}
}