package chess.exception;

public enum CommandException {

    COMMAND_INVALID("게임 커맨드가 올바르지 않습니다."),
    MOVING_COMMAND_INVALID("이동 방향이 올바르지 않습니다."),
    STATUS_COMMAND_INVALID("게임 시작 커맨드가 올바르지 않습니다.");

    private final String message;

    CommandException(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
