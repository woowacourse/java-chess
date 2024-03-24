package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Square;
import java.util.Objects;
import java.util.Set;

public abstract class Piece {

    protected final Color color;
    protected final PieceType pieceType;

    private final Square square;

    protected Piece(final Color color, final PieceType pieceType, Square square) {
        this.color = color;
        this.pieceType = pieceType;
        this.square = square;
    }

    public abstract Set<Square> movableSquaresFrom(final Square source);

    public boolean isAllyOf(Piece other) {
        return color == other.color;
    }

    protected Square locateSquare() {
        return square;
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
