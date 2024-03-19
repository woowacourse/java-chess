package chess.domain;

import chess.PieceColor;
import java.util.Objects;

public class PieceWithColor {

    private final Piece piece;
    private final PieceColor pieceColor;

    public PieceWithColor(final Piece piece, final PieceColor pieceColor) {
        this.piece = piece;
        this.pieceColor = pieceColor;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PieceWithColor that = (PieceWithColor) o;
        return piece == that.piece && pieceColor == that.pieceColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece, pieceColor);
    }
}
