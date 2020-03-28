package chess.view;

public class IllegalCommandException extends RuntimeException {
	public IllegalCommandException(String message) {
		super(message);
	}
}
