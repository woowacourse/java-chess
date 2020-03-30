package domain.command.exceptions;

public class CommandTypeException extends RuntimeException {
	public CommandTypeException(String message) {
		super(message);
	}
}
