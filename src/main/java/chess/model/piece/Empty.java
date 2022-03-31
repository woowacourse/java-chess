package chess.model.piece;

import chess.model.Color;
import chess.model.strategy.NonMovableStrategy;

public class Empty extends Piece {

    public Empty() {
        super(Color.NOTHING, new NonMovableStrategy());
    }

    @Override
    public double getPointValue() {
        return Point.EMPTY.getValue();
    }
}
