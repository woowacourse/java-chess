package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

public class Rook extends Piece {

    private static final String NAME = "r";

    public Rook(final Color color) {
        super(color, NAME);
    }

    @Override
    public void checkPieceMoveRange(final Board board, final Position from, final Position to) {
        if (from.getFile().equals(to.getFile()) || from.getRankNumber() == to.getRankNumber()) {
            checkAnyPiece(board, from, to);
            return;
        }
        throw new IllegalArgumentException("룩은 대각선으로 이동할 수 없습니다.");
    }

    private void checkAnyPiece(final Board board, final Position from, final Position to) {
        if (board.hasPieceInFile(from, to) || board.hasPieceInRank(from, to)) {
            throw new IllegalArgumentException("룩의 이동 경로에 기물이 존재합니다.");
        }
    }
}
