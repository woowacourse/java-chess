package chess.domain.piece;

import chess.domain.board.position.Position;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    final Team team;

    Piece(final Team team) {
        this.team = team;
    }

    public abstract boolean canMove(final Position sourcePosition,
                                    final Position targetPosition,
                                    final List<Position> otherPositions);

    public final boolean isSameTeam(final Piece anotherPiece) {
        return this.team == anotherPiece.team;
    }

    public abstract boolean isKing();

    public abstract boolean isPawn();

    public final boolean isTeamOf(final Team team) {
        return this.team == team;
    }

    public abstract double getScore();

    public abstract String getSymbol();

    public abstract boolean canPromote(final Position sourcePosition);

    public abstract Piece promote(final String promotionType);

    public abstract String getName();

    public String getTeam() {
        return team.name();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
