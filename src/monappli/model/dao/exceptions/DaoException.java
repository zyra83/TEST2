package monappli.model.dao.exceptions;

/**
 * Exception DAO personnalisée.
 * 
 * @author mickael
 *
 */
@SuppressWarnings("serial")
public class DaoException extends Exception {

	public DaoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DaoException(String message, Throwable cause) {
		super(String.format("%s => %s", cause.getMessage(), message), cause);
		// TODO Auto-generated constructor stub
	}

	public DaoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DaoException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
