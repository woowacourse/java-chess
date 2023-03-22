package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Shape;

public class Bishop extends ChessPiece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    Shape selectShape(Color color) {
        if (color.name().equals("BLACK")) {
            return Shape.BLACK_BISHOP;
        }
        if (color.name().equals("WHITE")) {
            return Shape.WHITE_BISHOP;
        }
        return Shape.BLANK;
    }

    //    public Bishop(Side side) {
//        super(side);
//    }

}
