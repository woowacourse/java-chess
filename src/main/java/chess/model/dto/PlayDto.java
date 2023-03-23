package chess.model.dto;

import chess.controller.GameCommand;
import chess.model.position.Position;

public class PlayDto {

    private final GameCommand gameCommand;
    private final Position source;
    private final Position target;

    public PlayDto(final GameCommand gameCommand, final Position source, final Position target) {
        this.gameCommand = gameCommand;
        this.source = source;
        this.target = target;
    }

    public GameCommand getGameCommand() {
        return gameCommand;
    }

    public Position getSource() {
        return source;
    }

    public Position getTarget() {
        return target;
    }
}
