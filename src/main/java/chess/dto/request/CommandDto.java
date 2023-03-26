package chess.dto.request;

import chess.view.GameCommand;

public final class CommandDto {

    private final GameCommand gameCommand;
    private final String startPosition;
    private final String endPosition;

    private CommandDto(GameCommand gameCommand, String startPosition, String endPosition) {
        this.gameCommand = gameCommand;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public static CommandDto of(GameCommand gameCommand, String startPosition, String endPosition) {
        return new CommandDto(gameCommand, startPosition, endPosition);
    }

    public GameCommand getGameCommand() {
        return gameCommand;
    }

    public String getStartPosition() {
        return startPosition;
    }

    public String getEndPosition() {
        return endPosition;
    }
}
