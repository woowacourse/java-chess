package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Movement;
import chess.domain.attribute.Square;
import java.util.Objects;
import java.util.Set;

public abstract class Piece {

    protected final Color color;
    protected final PieceType pieceType;
    private Square square;

    protected Piece(final Color color, final PieceType pieceType, Square square) {
        this.color = color;
        this.pieceType = pieceType;
        this.square = square;
    }

    abstract public Set<Square> findLegalMoves(Set<Piece> entirePieces);

    protected abstract Set<Movement> movements();

    public void moveTo(Square square) {
        this.square = square;
    }

    public boolean isAllyOf(Piece other) {
        return color == other.color;
    }

    public boolean isEnemyOf(Piece other) {
        return color != other.color;
    }

    public Square currentSquare() {
        return square;
    }

    public Color getColor() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Piece piece = (Piece) object;
        return getColor() == piece.getColor()
                && getPieceType() == piece.getPieceType()
                && Objects.equals(square, piece.square);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getPieceType(), square);
    }
}
