package chess.domain.piece;

import java.util.Objects;

public abstract class AbstractPiece implements Piece {

    private final PieceType type;
    private final Team team;

    protected AbstractPiece(final PieceType type, final Team team) {
        this.type = type;
        this.team = team;
    }

    public boolean isSameTeam(Piece other) {
        return this.team == other.getTeam();
    }

    public boolean isNotSameTeam(Piece other) {
        return !isSameTeam(other);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isNotEmpty() {
        return true;
    }

    @Override
    public PieceType getType() {
        return type;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractPiece piece = (AbstractPiece) o;
        return type == piece.type && team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, team);
    }
}
