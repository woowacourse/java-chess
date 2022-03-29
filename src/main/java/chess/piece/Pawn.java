package chess.piece;

import chess.position.Position;
import chess.position.Rank;
import java.math.BigDecimal;

public class Pawn extends Piece {

    private static final Rank BLACK_START_RANK = Rank.SEVEN;
    private static final Rank WHITE_START_RANK = Rank.TWO;

    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected Piece createNewPiece(Position to) {
        return new Pawn(getColor(), to);
    }

    @Override
    protected boolean isPossibleMovement(Position to) {
        return isUncapturablePosition(to) || isCapturablePosition(to);
    }

    public boolean isUncapturablePosition(Position to) {
        return isForward(to) && isVerticalWay(to) && isValidDistance(to);
    }

    private boolean isForward(Position to) {
        return getColor().isForward(getPosition(), to);
    }

    private boolean isVerticalWay(Position to) {
        return getPosition().isVerticalWay(to);
    }

    private boolean isValidDistance(Position to) {
        if (isVerticalWay(to)) {
            return getVerticalDistance(to) <= movableDistance();
        }
        return getVerticalDistance(to) == 1 && getHorizontalDistance(to) == 1;
    }

    private int getVerticalDistance(Position to) {
        return getPosition().getVerticalDistance(to);
    }

    private int movableDistance() {
        if (isStartPawnPosition()) {
            return 2;
        }
        return 1;
    }

    private boolean isStartPawnPosition() {
        if (getColor() == Color.BLACK) {
            return getPosition().isSameRank(BLACK_START_RANK);
        }
        return getPosition().isSameRank(WHITE_START_RANK);
    }

    private int getHorizontalDistance(Position to) {
        return getPosition().getHorizontalDistance(to);
    }

    public boolean isCapturablePosition(Position to) {
        return isForward(to) && isDiagonalWay(to) && isValidDistance(to);
    }

    private boolean isDiagonalWay(Position to) {
        return getPosition().isDiagonalWay(to);
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
