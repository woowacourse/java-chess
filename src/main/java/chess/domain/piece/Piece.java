package chess.domain.piece;

import java.util.Objects;
import java.util.Set;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;

public abstract class Piece {

    protected final Color color;
    protected final PieceType pieceType;

    protected Piece(final Color color, final PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    public abstract Set<Position> movablePositionsFrom(final Position source);

    public boolean isAllyOf(Piece other) {
        return color == other.color;
    }

    public Color getColor() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        return object instanceof Piece other
                && color == other.color
                && pieceType == other.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, pieceType);
    }
}
