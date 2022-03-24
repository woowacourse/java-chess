package chess.piece;

import chess.Position;
import java.util.Objects;

import chess.PieceColor;

public abstract class Piece {

    final PieceColor pieceColor;

    public Piece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public String getEmblem() {
        if (pieceColor == PieceColor.WHITE) {
            return getConcreteEmblem().toLowerCase();
        }
        return getConcreteEmblem();
    }

    public abstract String getConcreteEmblem();

    public abstract boolean isMovable(Position source, Position target);

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Piece piece = (Piece)o;
        return pieceColor == piece.pieceColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor);
    }
}
