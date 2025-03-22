package chess.piece;

import chess.Color;
import chess.position.Offset;

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
    public String toString() {
        return "킹";
    }
}
