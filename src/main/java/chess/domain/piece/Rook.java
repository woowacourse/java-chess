package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Shape;

public class Rook extends ChessPiece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    Shape selectShape(Color color) {
        if (color.name().equals("BLACK")) {
            return Shape.BLACK_ROOK;
        }
        if (color.name().equals("WHITE")) {
            return Shape.WHITE_ROOK;
        }
        return Shape.BLANK;
    }

    //    public Rook(Side side) {
//        super(side);
//    }
}
