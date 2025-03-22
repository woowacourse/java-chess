package chess.piece;

import chess.Color;
import chess.position.Offset;
import java.util.Objects;

public class King implements Piece {

    private final Color color;

    public King(final Color color) {
        this.color = color;
    }

    @Override
    public boolean canMove(final Offset offset, final boolean killFlag) {
        return offset.isVerticalOrHorizontal() && offset.hasOneMovement();
    }

    @Override
    public Color getColor() {
        return color;
    }


    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final King king = (King) o;
        return color == king.color;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(color);
    }

    @Override
    public String toString() {
        return color + "킹";
    }
}
