package chess.domain.Piece.state;

import chess.domain.Piece.Piece;
import chess.domain.Piece.team.Team;

public abstract class Started implements Piece {
    protected final String name;
    protected final Team team;

    protected Started(String name, Team team) {
        this.name = team.convertName(name);
        this.team = team;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public boolean isNotBlank() {
        return true;
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
