package chess.dto;

public class GameRequest {

    private final GameCommand command;
    private final MoveRequest moveRequest;

    private GameRequest(final GameCommand command, final MoveRequest moveRequest) {
        this.command = command;
        this.moveRequest = moveRequest;
    }

    public static GameRequest createCommand(final GameCommand command) {
        return new GameRequest(command, null);
    }

    public static GameRequest createCommand(final String source, final String target) {
        return new GameRequest(GameCommand.MOVE, new MoveRequest(source, target));
    }

    public GameCommand getCommand() {
        return command;
    }

    public MoveRequest getMoveRequest() {
        return moveRequest;
    }
}