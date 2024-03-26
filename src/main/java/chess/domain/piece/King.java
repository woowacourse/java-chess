package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.square.Square;

public class King extends Piece {

    private static final int STEP_LIMIT = 1;
    private static final String ERROR_CANNOT_REACH = "킹의 이동 방법으로 갈 수 없는 곳입니다.";
    private static final String ERROR_FRIENDLY_ON_TARGET = "킹의 목적지에 같은 색 기물이 존재합니다.";

    public King(final PieceColor color, final Square square) {
        super(color, square);
    }

    @Override
    public void move(final Board board, final Square target) {
        validateStepCount(target);
        validateFriendly(board, target);
        square = target;
    }

    private void validateStepCount(final Square target) {
        if (!isStepUnderLimit(target)) {
            throw new IllegalArgumentException(ERROR_CANNOT_REACH);
        }
    }

    private void validateFriendly(final Board board, final Square target) {
        if (board.existOnSquareWithColor(target, getColor())) {
            throw new IllegalArgumentException(ERROR_FRIENDLY_ON_TARGET);
        }
    }

    private boolean isStepUnderLimit(final Square target) {
        return square.distanceFileFrom(target) <= STEP_LIMIT &&
                square.distanceRankFrom(target) <= STEP_LIMIT;
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }
}
