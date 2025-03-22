package chess.piece;

import chess.Color;
import chess.position.Offset;

public class Bishop implements Piece {

    private final Color color;

    public Bishop(final Color color) {
        this.color = color;
    }

    @Override
    public boolean canMove(final Offset offset, final boolean killFlag) {
        return offset.isDiagonal();
    }

    @Override
    public Color getColor() {
        return color;
    }


    @Override
    public String toString() {
        return "숍";
    }
}
