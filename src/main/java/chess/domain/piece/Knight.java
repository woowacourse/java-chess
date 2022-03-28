package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public final class Knight extends Piece {

    private static final int FIRST_STEP = 2;
    private static final int SECOND_STEP = 1;
    private static final double KNIGHT_SCORE = 2.5;

    public Knight(final Color color) {
        super(color, PieceNotation.KNIGHT, KNIGHT_SCORE);
    }

    @Override
    public void checkMoveRange(final Board board, final Position from, final Position to) {
        final var fileDifference = Math.abs(from.getFileOrder() - to.getFileOrder());
        final var rankDifference = Math.abs(from.getRankNumber() - to.getRankNumber());

        if (rankDifference == FIRST_STEP && fileDifference == SECOND_STEP) {
            return;
        }
        if (rankDifference == SECOND_STEP && fileDifference == FIRST_STEP) {
            return;
        }
        throw new IllegalArgumentException("나이트는 두 칸 이동 후 90도 방향으로 한 칸 이동할 수 있습니다.");
    }
}
