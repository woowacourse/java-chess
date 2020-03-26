package chess.domain.piece.state;

import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

public abstract class Started implements Piece {
    protected final String name;
    protected final Team team;
    protected final Position position;

    protected Started(String name, Position position, Team team) {
        this.name = team.convertName(name);
        this.team = team;
        this.position = position;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return name;
    }
}
