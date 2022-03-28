package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

public final class King extends Piece {

    private static final int KING_SCORE = 0;
    private static final int ONE_STEP = 1;

    public King(final Color color) {
        super(color, PieceNotation.KING, KING_SCORE);
    }

    @Override
    public void checkMoveRange(final Board board, final Position from, final Position to) {
        final var fileDifference = Math.abs(from.getFileOrder() - to.getFileOrder());
        final var rankDifference = Math.abs(from.getRankNumber() - to.getRankNumber());

        if (fileDifference == 0 && rankDifference == ONE_STEP) {
            return;
        }
        if (fileDifference == ONE_STEP && rankDifference == 0) {
            return;
        }
        if (fileDifference == ONE_STEP && rankDifference == ONE_STEP) {
            return;
        }
        throw new IllegalArgumentException("킹은 모든 방향으로 한 칸 이동 가능합니다.");
    }
}
