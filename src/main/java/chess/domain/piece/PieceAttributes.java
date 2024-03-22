package chess.domain.piece;

import java.util.Objects;

public class PieceAttributes {

    private final PieceType pieceType;
    private final Color color;

    public PieceAttributes(PieceType pieceType, Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public boolean hasSameColorOf(Color color) {
        return this.color == color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PieceAttributes that = (PieceAttributes) o;
        return pieceType == that.pieceType && color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, color);
    }
}
