package chess.piece;

import chess.position.Position;
import java.math.BigDecimal;

public class Pawn extends Piece {

    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected Piece createNewPiece(Position to) {
        return new Pawn(getColor(), to);
    }

    @Override
    protected boolean isPossibleMovement(Position to, Pieces pieces) {
        if (!isForward(to)) {
            return false;
        }
        if (isUncapturablePosition(to)) {
            return !pieces.hasPieceByPosition(to) && !pieces.hasObstacleOnLinearPath(getPosition(), to);
        }
        if (isCapturablePosition(to)) {
            return pieces.hasPieceByPosition(to);
        }
        return false;
    }

    private boolean isForward(Position to) {
        return getColor().isForward(getPosition(), to);
    }

    private boolean isUncapturablePosition(Position to) {
        return getPosition().isVerticalWay(to) && isValidDistance(to);
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

    private boolean isCapturablePosition(Position to) {
        return getPosition().isDiagonalWay(to) && getPosition().getVerticalDistance(to) == 1;
    }

    @Override
    public BigDecimal getPoint() {
        return BigDecimal.ONE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
