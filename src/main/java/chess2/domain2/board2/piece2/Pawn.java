package chess2.domain2.board2.piece2;

import static chess2.domain2.board2.position.Direction.DOWN;
import static chess2.domain2.board2.position.Direction.DOWN_LEFT;
import static chess2.domain2.board2.position.Direction.DOWN_RIGHT;
import static chess2.domain2.board2.position.Direction.UP;
import static chess2.domain2.board2.position.Direction.UP_LEFT;
import static chess2.domain2.board2.position.Direction.UP_RIGHT;
import static chess2.domain2.board2.piece2.Color.WHITE;

import chess2.domain2.board2.position.Direction;
import chess2.domain2.board2.position.Position;
import java.util.List;

public final class Pawn extends Piece {

    private static final int WHITE_INIT_RANK_IDX = 1;
    private static final int BLACK_INIT_RANK_IDX = 6;

    private static final int ADJACENT_DISTANCE = 1;
    private static final int DOUBLE_STEP_DISTANCE = 2;

    private static final List<Direction> WHITE_ATTACK_DIRECTION = List.of(UP_LEFT, UP_RIGHT);
    private static final List<Direction> BLACK_ATTACK_DIRECTION = List.of(DOWN_LEFT, DOWN_RIGHT);

    public Pawn(Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public boolean canMove(Position from, Position to) {
        if (!isForward(from, to)) {
            return false;
        }
        return isOneOrTwoStepsAway(from, to);
    }

    private boolean isForward(Position from, Position to) {
        if (hasColorOf(WHITE)) {
            return from.checkDirection(to, UP);
        }
        return from.checkDirection(to, DOWN);
    }

    private boolean isOneOrTwoStepsAway(Position from, Position to) {
        int rankDiff = from.rankDifference(to);
        if (rankDiff == DOUBLE_STEP_DISTANCE) {
            return atInitialPosition(from);
        }
        return rankDiff == ADJACENT_DISTANCE;
    }

    private boolean atInitialPosition(Position currentPosition) {
        if (hasColorOf(Color.BLACK)) {
            return currentPosition.hasRankIdxOf(BLACK_INIT_RANK_IDX);
        }
        return currentPosition.hasRankIdxOf(WHITE_INIT_RANK_IDX);
    }

    @Override
    protected boolean isAttackableRoute(Position from, Position to) {
        if (!isAttackDirection(from, to)) {
            return false;
        }
        return isDiagonallyAdjacentRoute(from, to);
    }

    private boolean isDiagonallyAdjacentRoute(Position from, Position to) {
        int fileDiff = from.fileDifference(to);
        int rankDiff = from.rankDifference(to);

        return fileDiff == ADJACENT_DISTANCE && rankDiff == ADJACENT_DISTANCE;
    }

    private boolean isAttackDirection(Position from, Position to) {
        if (hasColorOf(WHITE)) {
            return WHITE_ATTACK_DIRECTION.stream()
                    .anyMatch(direction -> from.checkDirection(to, direction));
        }
        return BLACK_ATTACK_DIRECTION.stream()
                        .anyMatch(direction -> from.checkDirection(to, direction));
    }
}
