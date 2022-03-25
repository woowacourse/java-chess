package chess.piece;

import chess.position.Transition;

public class Rook extends Piece{

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean isMovablePosition(Transition transition) {
        return transition.isVerticalWay() || transition.isHorizontalWay();
    }
}
