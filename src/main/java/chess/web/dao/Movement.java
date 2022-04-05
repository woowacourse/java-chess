package chess.web.dao;

import chess.domain.piece.property.Team;
import chess.domain.position.Position;

public final class Movement {

    private String moveId;
    private String gameId;
    private final Position source;
    private final Position target;
    private Team team;

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

    public String getMoveId() {
        return moveId;
    }

    public void setMoveId(String moveId) {
        this.moveId = moveId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
