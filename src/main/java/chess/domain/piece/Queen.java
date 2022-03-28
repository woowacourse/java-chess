package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

public class Queen extends Piece {

    private static final String NAME = "q";
    private static final double SCORE = 9;

    public Queen(final Color color) {
        super(color, NAME, SCORE);
    }

    @Override
    public void checkPieceMoveRange(final Board board, final Position from, final Position to) {
        if (isVertical(from, to) || isHorizontal(from, to)) {
            checkAnyPiece(board, from, to);
            return;
        }
        if (!isDiagonal(from, to)) {
            throw new IllegalArgumentException("퀸은 상하좌우 대각선 방향으로만 이동할 수 있습니다.");
        }
        board.checkPieceInDiagonal(from, to);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
