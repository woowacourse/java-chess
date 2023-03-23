package chess.controller;

import chess.model.position.Position;

public class MoveRequest {

    private final GameCommand gameCommand;
    private final Position source;
    private final Position target;

    public MoveRequest(final GameCommand gameCommand, final Position source,
                       final Position target) {
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
