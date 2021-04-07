package chess.exception;

public class CommandFormatException extends RuntimeException {
    private static final String MESSAGE = "명령어 형식이 부정확 합니다.";

    public CommandFormatException() {
        super(MESSAGE);
    }

}
