package chess.domain.piece;

import java.util.Objects;

import chess.domain.PieceType;
import chess.domain.board.Position;

public abstract class Piece {

    private final PieceType pieceType;
    private final Color color;

    protected Piece(final PieceType pieceType, final Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public abstract Direction findValidDirection(Position current, Position target);

    public boolean isSamePiece(final PieceType expected) {
        return pieceType == expected;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Piece)) {
            return false;
        }
        Piece piece = (Piece)o;
        return Objects.equals(pieceType, piece.pieceType) && Objects.equals(color, piece.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType);
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }
}
