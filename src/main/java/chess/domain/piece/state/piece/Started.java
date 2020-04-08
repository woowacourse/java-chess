package chess.domain.piece.state.piece;

import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;

public abstract class Started implements Piece {
    protected final String name;
    protected final Team team;

    protected Started(String name, Team team) {
        this.name = Team.convertName(name, team);
        this.team = team;
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
