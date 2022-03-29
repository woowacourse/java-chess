package chess.piece;

import chess.position.Position;
import chess.position.Rank;
import java.math.BigDecimal;

public class Pawn extends Piece {

    private static final Rank BLACK_START_RANK = Rank.SEVEN;
    private static final Rank WHITE_START_RANK = Rank.TWO;

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public MovementCondition identifyMovementCondition(Position from, Position to) {
        if (isForward(from, to) && isVerticalWay(from, to) && isValidDistance(from, to)) {
            return MovementCondition.UNCATCABLE_AND_UNOBSTRUCTED;
        }

        if (isForward(from, to) && isDiagonalWay(from, to) && isValidDistance(from, to)) {
            return MovementCondition.CATCHABLE;
        }

        return MovementCondition.IMPOSSIBLE;
    }

    private boolean isForward(Position from, Position to) {
        return getColor().isForward(from, to);
    }

    private boolean isVerticalWay(Position from, Position to) {
        return from.isVerticalWay(to);
    }

    private boolean isValidDistance(Position from, Position to) {
        if (isVerticalWay(from, to)) {
            return getVerticalDistance(from, to) <= movableDistance(from);
        }
        return getVerticalDistance(from, to) == 1 && getHorizontalDistance(from, to) == 1;
    }

    private int getVerticalDistance(Position from, Position to) {
        return from.getVerticalDistance(to);
    }

    private int movableDistance(Position from) {
        if (isStartPawnPosition(from)) {
            return 2;
        }
        return 1;
    }

    private boolean isStartPawnPosition(Position position) {
        if (getColor() == Color.BLACK) {
            return position.isSameRank(BLACK_START_RANK);
        }
        return position.isSameRank(WHITE_START_RANK);
    }

    private int getHorizontalDistance(Position from, Position to) {
        return from.getHorizontalDistance(to);
    }

    private boolean isDiagonalWay(Position from, Position to) {
        return from.isDiagonalWay(to);
    }

    @Override
    public BigDecimal getPoint() {
        return BigDecimal.ONE;
    }
}
