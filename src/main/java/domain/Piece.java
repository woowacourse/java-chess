package domain;

import java.util.Objects;

public class Piece {

    private final PieceType pieceType;
    private final Color color;

    public Piece(final PieceType pieceType, final Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public Piece(final PieceType pieceType) {
        this.pieceType = pieceType;
        this.color = Color.NONE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return pieceType == piece.pieceType && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, color);
    }
}
