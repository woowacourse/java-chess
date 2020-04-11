package chess.dao;

public class DataAccessException extends RuntimeException {
	public DataAccessException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public DataAccessException(String message) {
		super(message);
	}
}
