package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Shape;

public class Pawn extends ChessPiece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    Shape selectShape(Color color) {
        if (color.name().equals("BLACK")) {
            return Shape.BLACK_PAWN;
        }
        if (color.name().equals("WHITE")) {
            return Shape.WHITE_PAWN;
        }
        return Shape.BLANK;
    }

    //    public Pawn(Side side) {
//        super(side);
//    }
}
