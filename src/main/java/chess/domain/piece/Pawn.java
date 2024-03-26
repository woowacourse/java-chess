package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.square.Rank;
import chess.domain.square.Square;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private static final int FIRST_STEP_LIMIT = 2;
    private static final int STEP_LIMIT = 1;
    private static final Rank FIRST_RANK_BLACK = Rank.SEVEN;
    private static final Rank FIRST_RANK_WHITE = Rank.TWO;
    private static final String ERROR_CANNOT_REACH = "폰의 이동 방법으로 갈 수 없는 곳입니다.";
    private static final String ERROR_OBSTACLE_ON_PATH = "폰의 이동 경로 중 장애물이 존재합니다.";

    public Pawn(final PieceColor color, final Square square) {
        super(color, square);
    }

    @Override
    public void move(final Board board, final Square target) {
        if (isForwardDiagonal(target) && existEnemyOnTarget(board, target)) {
            validateAttackStepLimit(target);
            square = target;
            return;
        }
        validateMove(board, target);
        square = target;
    }

    private void validateAttackStepLimit(final Square target) {
        if (square.distanceRankFrom(target) > STEP_LIMIT) {
            throw new IllegalArgumentException(ERROR_CANNOT_REACH);
        }
    }

    private void validateMove(final Board board, final Square target) {
        validateMoveDirection(target);
        validateMoveStepLimit(target);
        validateMoveObstacle(board, target);
    }

    private void validateMoveDirection(final Square target) {
        if (!isForward(target) || !square.isSameFile(target)) {
            throw new IllegalArgumentException(ERROR_CANNOT_REACH);
        }
    }

    private void validateMoveStepLimit(final Square target) {
        if ((!isFirstStep() && square.distanceRankFrom(target) > STEP_LIMIT) ||
                square.distanceRankFrom(target) > FIRST_STEP_LIMIT) {
            throw new IllegalArgumentException(ERROR_CANNOT_REACH);
        }
    }

    private void validateMoveObstacle(final Board board, final Square target) {
        List<Square> path = new ArrayList<>(square.generatePath(target));
        path.add(target);
        if (path.stream().anyMatch(board::existOnSquare)) {
            throw new IllegalArgumentException(ERROR_OBSTACLE_ON_PATH);
        }
    }

    private boolean isForward(final Square target) {
        if (getColor() == PieceColor.BLACK) {
            return square.isUpperThan(target);
        }
        return square.isLowerThan(target);
    }

    private boolean isFirstStep() {
        if (getColor() == PieceColor.BLACK) {
            return square.isSameRank(FIRST_RANK_BLACK);
        }
        return square.isSameRank(FIRST_RANK_WHITE);
    }

    private boolean isForwardDiagonal(Square target) {
        return isForward(target) && square.isSameDiagonal(target);
    }

    private boolean existEnemyOnTarget(Board board, Square target) {
        return board.existOnSquareWithColor(target, getColor().opposite());
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }
}
