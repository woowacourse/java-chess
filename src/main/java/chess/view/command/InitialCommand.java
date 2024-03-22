package chess.view.command;

public class InitialCommand {

    private final CommandType commandType;
    private final String description;

    public InitialCommand(CommandType commandType, String description) {
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

    public boolean isMove() {
        return commandType == CommandType.MOVE;
    }
}
