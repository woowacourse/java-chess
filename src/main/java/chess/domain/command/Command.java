package chess.domain.command;

public class Command {

    private static final String NOT_MOVE_TYPE_EXCEPTION_MESSAGE = "이동 명령이 아닙니다.";
    private final CommandType type;
    private final String description;

    public Command(CommandType type, String description) {
        this.type = type;
        this.description = description;
    }

    public boolean hasTypeOf(CommandType type) {
        return this.type == type;
    }

    public MoveRoute toMoveRoute() {
        if (type != CommandType.MOVE) {
            throw new UnsupportedOperationException(NOT_MOVE_TYPE_EXCEPTION_MESSAGE);
        }
        return type.toMoveRoute(description);
    }
}
