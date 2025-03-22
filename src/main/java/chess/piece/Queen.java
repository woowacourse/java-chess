package chess.piece;

import chess.Color;
import chess.position.Offset;

public class Queen implements Piece {

    private final Color color;

    public Queen(final Color color) {
        this.color = color;
    }

    @Override
    public boolean canMove(final Offset offset, final boolean killFlag) {
        return offset.isDiagonal() || offset.isVerticalOrHorizontal();
    }

    @Override
    public Color getColor() {
        return color;
    }


    @Override
    public String toString() {
        return "퀸";
    }
}
