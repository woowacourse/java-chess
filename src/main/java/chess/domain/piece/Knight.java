package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.square.Square;

public class Knight extends Piece {

    private static final int SIDE_STEP = 1;
    private static final int STRAIGHT_STEP = 2;
    private static final String ERROR_CANNOT_REACH = "나이트의 이동 방법으로 갈 수 없는 곳입니다.";
    private static final String ERROR_FRIENDLY_ON_TARGET = "나이트의 목적지에 같은 색 기물이 존재합니다.";

    public Knight(PieceColor color, Square square) {
        super(color, square);
    }

    @Override
    public void move(Board board, Square target) {
        validateStraightSide(target);
        validateFriendly(board, target);
        square = target;
    }

    private void validateStraightSide(Square target) {
        if (!isStraightRankSideFile(target) && !isStraightFileSideRank(target)) {
            throw new IllegalArgumentException(ERROR_CANNOT_REACH);
        }
    }

    private void validateFriendly(Board board, Square target) {
        if (board.existOnSquareWithColor(target, getColor())) {
            throw new IllegalArgumentException(ERROR_FRIENDLY_ON_TARGET);
        }
    }

    private boolean isStraightRankSideFile(Square target) {
        return square.distanceRankFrom(target) == STRAIGHT_STEP && square.distanceFileFrom(target) == SIDE_STEP;
    }

    private boolean isStraightFileSideRank(Square target) {
        return square.distanceFileFrom(target) == STRAIGHT_STEP && square.distanceRankFrom(target) == SIDE_STEP;
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}
