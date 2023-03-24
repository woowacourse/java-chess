package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.CheckablePaths;
import chess.domain.Position;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected Camp camp;
    protected PieceType type;

    public Piece(final Camp camp, final PieceType type) {
        this.camp = camp;
        this.type = type;
    }

    public abstract CheckablePaths findCheckablePaths(Position current);

    public abstract boolean canMoveToEmpty(Position source, Position dest);

    public abstract boolean canAttack(Position source, Position dest, Piece target);

    public abstract double sumPointsOf(List<Position> existingPositions);

    public boolean isDifferentCamp(Camp other) {
        return this.camp != other;
    }

    public boolean isSameCamp(final Camp camp) {
        return this.camp == camp;
    }

    public Camp getColor() {
        return camp;
    }

    public PieceType getType() {
        return type;
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
        return camp == piece.camp && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(camp, type);
    }

}
