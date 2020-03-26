package domain.pieces.exceptions;

public class CanNotMoveException extends RuntimeException {
	public CanNotMoveException() {
		super();
	}

	public CanNotMoveException(String message) {
		super(message);
	}
}
