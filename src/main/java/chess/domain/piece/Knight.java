package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Shape;

public class Knight extends ChessPiece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    Shape selectShape(Color color) {
        if (color.name().equals("BLACK")) {
            return Shape.BLACK_KNIGHT;
        }
        if (color.name().equals("WHITE")) {
            return Shape.WHITE_KNIGHT;
        }
        return Shape.BLANK;
    }

    //    public Knight(Side side) {
//        super(side);
//    }
}
