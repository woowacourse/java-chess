package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

public final class Rook extends Piece {

    private static final int ROOK_SCORE = 5;

    public Rook(final Color color) {
        super(color, PieceNotation.ROOK, ROOK_SCORE);
    }

    @Override
    public void checkMoveRange(final Board board, final Position from, final Position to) {
        if (isLinerMove(from, to)) {
            board.checkHasPieceInLiner(from, to);
            return;
        }
        throw new IllegalArgumentException("룩은 대각선으로 이동할 수 없습니다.");
    }
}
