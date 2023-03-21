package chess.domain.pieces;

import chess.domain.board.Position;
import chess.domain.strategy.Route;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    private final Team team;

    public Piece(final Team team) {
        this.team = team;
    }

    public abstract void canMove(final Position start, final Position end, final boolean isAttack);

    public abstract Route generateRoute(final Position source, final Position destination);

    public Team getTeam() {
        return team;
    }

    public boolean isEmpty() {
        return team.isEmpty();
    }

    public boolean isWhiteTeam() {
        return team.isWhiteTeam();
    }

    public boolean isBlackTeam() {
        return team.isBlackTeam();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
