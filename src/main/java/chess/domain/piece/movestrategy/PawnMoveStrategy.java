package chess.domain.piece.movestrategy;

import chess.domain.board.Direction;
import chess.domain.board.MoveOrder;
import chess.domain.piece.Color;
import chess.domain.position.Position;

import java.util.List;

public final class PawnMoveStrategy extends MoveStrategy {

    private static final int WHITE_INIT_RANK = 2;
    private static final int BLACK_INIT_RANK = 7;
    private static final int ONE_STEP = 1;
    private static final int TWO_STEP = 2;

    private final Color color;

    public PawnMoveStrategy(final Color color) {
        super(pawnDirections(color));
        this.color = color;
    }

    private static List<Direction> pawnDirections(final Color color) {
        if (color.isWhite()) {
            return Direction.WHITE_PAWN_DIRECTION;
        }
        return Direction.BLACK_PAWN_DIRECTION;
    }

    @Override
    public boolean canMove(final MoveOrder moveOrder) {
        if (directions.contains(moveOrder.getDirection())) {
            return isOneStep(moveOrder) || isTwoStep(moveOrder);
        }
        return false;
    }

    private boolean isOneStep(final MoveOrder moveOrder) {
        if (moveOrder.getStepCount() == ONE_STEP) {
            return canMoveOneStep(moveOrder);
        }
        return false;
    }

    private boolean canMoveOneStep(final MoveOrder moveOrder) {
        if (moveOrder.isEmptyDestination()) {
            return moveOrder.isVertical();
        }
        return moveOrder.isDiagonal();
    }

    private boolean isTwoStep(final MoveOrder moveOrder) {
        if (moveOrder.getStepCount() == TWO_STEP) {
            return canMoveTwoStep(moveOrder);
        }
        return false;
    }


    private boolean canMoveTwoStep(final MoveOrder moveOrder) {
        return isFirstMove(moveOrder.getFromPosition()) && moveOrder.isVertical() && moveOrder.isEmptyDestination();
    }

    private boolean isFirstMove(final Position from) {
        return from.getRankNumber() == getPawnInitRank();
    }

    private int getPawnInitRank() {
        if (color.isWhite()) {
            return WHITE_INIT_RANK;
        }
        return BLACK_INIT_RANK;
    }
}
