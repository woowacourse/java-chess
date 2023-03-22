package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Shape;

public class King extends ChessPiece {

    public King(Color color) {
        super(color);
    }

    @Override
    Shape selectShape(Color color) {
        if (color.name().equals("BLACK")) {
            return Shape.BLACK_KING;
        }
        if (color.name().equals("WHITE")) {
            return Shape.WHITE_KING;
        }
        return Shape.BLANK;
    }


    //    public King(Side side) {
//        super(side);
//    }
}
