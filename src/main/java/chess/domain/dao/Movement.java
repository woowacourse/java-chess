package chess.domain.dao;

import chess.domain.position.Position;

public final class Movement {

    private String gameId;
    private final Position source;
    private final Position target;

    public Movement(Position source, Position target) {
        this.source = source;
        this.target = target;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getSource() {
        return source.toString();
    }

    public String getTarget() {
        return target.toString();
    }
}
