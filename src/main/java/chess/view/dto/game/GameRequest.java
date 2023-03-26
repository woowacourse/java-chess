package chess.view.dto.game;

public class GameRequest {

    private final GameCommandType commandType;
    private final MoveRequest moveRequest;

    private GameRequest(GameCommandType commandType, MoveRequest moveRequest) {
        this.commandType = commandType;
        this.moveRequest = moveRequest;
    }

    public static GameRequest createSingleCommand(GameCommandType commandType) {
        return new GameRequest(commandType, null);
    }

    public static GameRequest createMoveCommand(String source, String target) {
        return new GameRequest(GameCommandType.MOVE, new MoveRequest(source, target));
    }

    public GameCommandType getCommandType() {
        return commandType;
    }

    public MoveRequest getMoveRequest() {
        return moveRequest;
    }
}
