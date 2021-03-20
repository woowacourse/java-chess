package chess.exception;

public class InvalidCommandException extends ChessException {
    private static final String INVALID_COMMAND_MESSAGE = "유효하지 않은 명령입니다.";

    public InvalidCommandException() {
        super(INVALID_COMMAND_MESSAGE);
    }
}
