package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

public class Bishop extends Piece {

    private static final String NAME = "b";
    private static final double SCORE = 3;

    public Bishop(final Color color) {
        super(color, NAME, SCORE);
    }

    @Override
    public void checkPieceMoveRange(final Board board, final Position from, final Position to) {
        if (!isDiagonal(from, to)) {
            throw new IllegalArgumentException("비숍은 대각선 방향만 이동할 수 있습니다.");
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
