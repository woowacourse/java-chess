package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Shape;

public class Queen extends ChessPiece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    Shape selectShape(Color color) {
        if (color.name().equals("BLACK")) {
            return Shape.BLACK_QUEEN;
        }
        if (color.name().equals("WHITE")) {
            return Shape.WHITE_QUEEN;
        }
        return Shape.BLANK;
    }

    //    public Queen(Side side) {
//        super(side);
//    }
}
