package chess.view.command;

public class Command {

    private final CommandType commandType;
    private final String description;

    public Command(final CommandType commandType, final String description) {
        this.commandType = commandType;
        this.description = description;
    }

    public MoveCommand toMoveCommand() {
        if (isMove()) {
            return new MoveCommand(description);
        }
        throw new IllegalStateException("이동 명령어에서만 실행할 수 있습니다.");
    }

    public boolean isStart() {
        return commandType == CommandType.START;
    }

    public boolean isEnd() {
        return commandType == CommandType.END;
    }

    public boolean isMove() {
        return commandType == CommandType.MOVE;
    }
}
