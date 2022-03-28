package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

public final class Queen extends Piece {

    private static final int QUEEN_SCORE = 9;

    public Queen(final Color color) {
        super(color, PieceNotation.QUEEN, QUEEN_SCORE);
    }

    @Override
    public void checkMoveRange(final Board board, final Position from, final Position to) {
        if (isLinerMove(from, to)) {
            board.checkHasPieceInLiner(from, to);
            return;
        }
        if (isDiagonalMove(from, to)) {
            board.checkHasPieceInDiagonal(from, to);
            return;
        }
        throw new IllegalArgumentException("퀸은 상하좌우 대각선 방향으로만 이동할 수 있습니다.");
    }
}
