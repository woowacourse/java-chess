package chess.domain.piece;

import java.util.Objects;

public class Piece {

    private final PieceType pieceType;
    private final ColorType colorType;

    public Piece(PieceType pieceType, ColorType colorType) {
        this.pieceType = pieceType;
        this.colorType = colorType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return pieceType == piece.pieceType && colorType == piece.colorType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, colorType);
    }
}
