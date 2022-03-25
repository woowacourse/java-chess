package chess.piece;

import chess.position.Transition;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isMovablePosition(Transition transition) {
        return transition.isVerticalWay() && isForward(transition) && isValidDistance(transition);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    private boolean isForward(Transition transition) {
        return getColor().isForward(transition);
    }

    private boolean isValidDistance(Transition transition) {
        return transition.getVerticalDistance() <= movableDistance(transition);
    }

    private int movableDistance(Transition transition) {
        if (isStartPawnPosition(transition)) {
            return 2;
        }
        return 1;
    }

    private boolean isStartPawnPosition(Transition transition) {
        return getColor().isStartPawnPosition(transition.getFrom());
    }
}
