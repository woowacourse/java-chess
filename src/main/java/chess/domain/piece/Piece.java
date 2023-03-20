package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.move.enums.MoveEnum;
import chess.domain.team.Team;

public abstract class Piece {

    private final Team team;

    protected Piece(final Team team) {
        this.team = team;
    }

    public abstract String name();

    public abstract boolean movable(final MoveEnum move);

    public abstract boolean movableByCount(final int count);

    public boolean isSameTeam(final Team team) {
        return this.team.equals(team);
    }

    public Team team() {
        return team;
    }
}
