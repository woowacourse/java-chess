package chess.piece;

import chess.Color;
import chess.position.Offset;

public class Rook implements Piece{

    private final Color color;

    public Rook(final Color color) {
        this.color = color;
    }

    @Override
    public boolean canMove(final Offset offset, final boolean killFlag) {
        return offset.isVerticalOrHorizontal();
    }

    @Override
    public Color getColor() {
        return color;
    }


    @Override
    public String toString() {
        return "룩";
    }
}
