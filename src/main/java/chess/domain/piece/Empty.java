package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Shape;

public class Empty extends ChessPiece {

    public Empty() {
        super(Color.BLANK);
    }

    @Override
    Shape selectShape(Color color) {
        return Shape.BLANK;
    }

    //    public Empty(Side side) {
//        super(side);
//    }
}
