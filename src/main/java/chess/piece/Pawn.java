package chess.piece;

import chess.position.Position;

public class Pawn extends Piece {

    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected boolean isMovablePosition(Position to) {
        return isVerticalWay(to) && isForward(to) && isValidDistance(to);
    }

    private boolean isVerticalWay(Position to) {
        return getPosition().isVerticalWay(to);
    }

    private boolean isForward(Position to) {
        return getColor().isForward(getPosition(), to);
    }

    private boolean isValidDistance(Position to) {
        if (getColor().isFirstMove(getPosition())) {
            return getPosition().getVerticalDistance(to) <= 2;
        }
        return getPosition().getVerticalDistance(to) == 1;
    }
}
