package chess.piece;

import chess.position.Transition;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean isMovablePosition(Transition transition) {
        return transition.isDiagonalWay();
    }
}
