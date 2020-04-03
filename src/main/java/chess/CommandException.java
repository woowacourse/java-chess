package chess;

public class CommandException extends RuntimeException {
    private static final String NOT_EXIST_COMMAND_EXCEPTION_MESSAGE = "잘못된 명령어를 입력하셨습니다.";

    public CommandException() {
        super(NOT_EXIST_COMMAND_EXCEPTION_MESSAGE);
    }
}
