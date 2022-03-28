package chess.model.piece;

import chess.model.Color;
import chess.model.Square;
import chess.model.piece.strategy.NonMovableStrategy;

public class Empty extends Piece {

    public Empty(Square square) {
        super(Color.NOTHING, square, new NonMovableStrategy());
    }

    @Override
    public Point getPoint() {
        return Point.EMPTY;
    }
}
