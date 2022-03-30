package chess.domain.piece.notation;

import java.util.Objects;

public class ColorNotation {

    private final Color color;
    private final PieceNotation pieceNotation;

    public ColorNotation(final Color color, final PieceNotation pieceNotation) {
        this.color = color;
        this.pieceNotation = pieceNotation;
    }

    public PieceNotation getPieceNotation() {
        return pieceNotation;
    }

    public double getScore() {
        return pieceNotation.getScore();
    }

    public boolean isSameColor(final Color other) {
        return this.color == other;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorNotation that = (ColorNotation) o;
        return color == that.color && pieceNotation == that.pieceNotation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, pieceNotation);
    }
}
