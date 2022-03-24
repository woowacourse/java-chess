package chess.piece;

import chess.position.Position;

public class Pawn extends Piece {

    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isMovablePosition(Position to) {
        return isVerticalWay(to) && isForward(to) && isValidDistance(to);
    }

    private boolean isVerticalWay(Position to) {
        return getPosition().isVerticalWay(to);
    }

    private boolean isForward(Position to) {
        return getColor().isForward(getPosition(), to);
    }

    private boolean isValidDistance(Position to) {
        return getPosition().getVerticalDistance(to) <= movableDistance();
    }

    private int movableDistance() {
        if (isStartPawnPosition()) {
            return 2;
        }
        return 1;
    }

    private boolean isStartPawnPosition() {
        return getColor().isStartPawnPosition(getPosition());
    }
}
