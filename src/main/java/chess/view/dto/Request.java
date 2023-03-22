package chess.view.dto;

public class Request {

    private final CommandType commandType;
    private final MoveRequest moveRequest;

    private Request(CommandType commandType, MoveRequest moveRequest) {
        this.commandType = commandType;
        this.moveRequest = moveRequest;
    }

    public static Request createSingleCommand(CommandType commandType) {
        return new Request(commandType, null);
    }

    public static Request createMoveCommand(String source, String target) {
        return new Request(CommandType.MOVE, new MoveRequest(source, target));
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public MoveRequest getMoveRequest() {
        return moveRequest;
    }
}
