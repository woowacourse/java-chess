package chess.domain.piece.property;

import java.util.Objects;

public class PieceNameAndColor {

    private String pieceNameAndColor;

    private PieceNameAndColor(String pieceNameAndColor) {
        this.pieceNameAndColor = pieceNameAndColor;
    }

    public static PieceNameAndColor of(String pieceNameAndColor) {
        return new PieceNameAndColor(pieceNameAndColor);
    }

    public String getPieceNameAndColor() {
        return pieceNameAndColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PieceNameAndColor)) {
            return false;
        }
        PieceNameAndColor that = (PieceNameAndColor) o;
        return Objects.equals(getPieceNameAndColor(), that.getPieceNameAndColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPieceNameAndColor());
    }

    @Override
    public String toString() {
        return pieceNameAndColor;
    }
}
