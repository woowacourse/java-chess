package chess.domain.chessboard;

import java.util.Objects;

public class Square {

    private final Lettering lettering;
    private final Numbering numbering;

    public Square(Lettering lettering, Numbering numbering) {
        this.lettering = lettering;
        this.numbering = numbering;
    }

    public boolean isForwardMost() {
        return numbering == Numbering.EIGHT;
    }

    public boolean isBackwardMost() {
        return numbering == Numbering.ONE;
    }

    public boolean isLeftMost() {
        return lettering == Lettering.A;
    }

    public boolean isRightMost() {
        return lettering == Lettering.H;
    }

    public Lettering getLettering() {
        return lettering;
    }

    public Numbering getNumbering() {
        return numbering;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Square square = (Square) object;
        return lettering == square.lettering && numbering == square.numbering;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lettering, numbering);
    }
}
