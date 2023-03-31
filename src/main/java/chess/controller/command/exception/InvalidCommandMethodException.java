package chess.controller.command.exception;

public class InvalidCommandMethodException extends IllegalArgumentException {

    public InvalidCommandMethodException() {
        super("해당 커멘드에서 사용할 수 없는 명령어입니다.");
    }
}
