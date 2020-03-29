package domain.pieces.exceptions;

public class CanNotAttackException extends RuntimeException {
	public CanNotAttackException() {
		super();
	}

	public CanNotAttackException(String message) {
		super(message);
	}
}
