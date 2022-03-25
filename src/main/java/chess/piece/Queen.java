package chess.piece;

import chess.position.Transition;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean isMovablePosition(Transition transition) {
        return transition.isDiagonalWay() || transition.isVerticalWay()
            || transition.isHorizontalWay();
    }
}
