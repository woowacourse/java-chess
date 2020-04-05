package chess.exception;

public class CommandException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "잘못된 명령어를 입력하셨습니다.";

    public CommandException() {
        super(EXCEPTION_MESSAGE);
    }
}
