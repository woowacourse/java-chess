package chess.piece;

import chess.position.Position;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isPossibleMovement(Position from, Position to ) {
        return from.isVerticalWay(to) && isForward(from, to) && isValidDistance(from, to);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    private boolean isForward(Position from, Position to) {
        return getColor().isForward(from, to);
    }

    private boolean isValidDistance(Position from, Position to) {
        return from.getVerticalDistance(to) <= movableDistance(from);
    }

    private int movableDistance(Position from) {
        if (isStartPawnPosition(from)) {
            return 2;
        }
        return 1;
    }

    private boolean isStartPawnPosition(Position from) {
        return getColor().isStartPawnPosition(from);
    }
}
