package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

public final class Bishop extends Piece {

    private static final int BISHOP_SCORE = 3;

    public Bishop(final Color color) {
        super(color, PieceNotation.BISHOP, BISHOP_SCORE);
    }

    @Override
    public void checkMoveRange(final Board board, final Position from, final Position to) {
        if (isDiagonalMove(from, to)) {
            board.checkHasPieceInDiagonal(from, to);
            return;
        }
        throw new IllegalArgumentException("비숍은 대각선 방향만 이동할 수 있습니다.");
    }
}
