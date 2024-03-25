package domain.piece;

import domain.Score;
import domain.Team;
import domain.square.Square;

import java.util.Objects;

public abstract class Piece {

    private final PieceType pieceType;
    private final Team team;

    protected Piece(final Team team, final PieceType pieceType) {
        this.team = team;
        this.pieceType = pieceType;
    }

    public abstract boolean canMove(Square source, Square target);

    public abstract boolean canAttack(final Square source, final Square target);

    public final boolean canNotMove(final Square source, final Square target) {
        return !canMove(source, target);
    }

    public final boolean canNotAttack(final Square source, final Square target) {
        return !canAttack(source, target);
    }

    public final boolean isSameTeam(final Piece other) {
        return this.team == other.team;
    }

    public final boolean isOppositeTeam(final Team other) {
        return team != other;
    }

    public final boolean isTeam(final Team other) {
        return team == other;
    }

    public Score getScore() {
        return pieceType.getScore();
    }

    public Team team() {
        return team;
    }

    public PieceType pieceType() {
        return pieceType;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Piece piece = (Piece) o;
        return pieceType == piece.pieceType && team == piece.team;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(pieceType, team);
    }
}
