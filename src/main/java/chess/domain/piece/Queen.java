package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class Queen extends Piece {

    private static final String NAME = "q";

    public Queen(final Color color) {
        super(color, NAME, 9);
    }

    @Override
    public void checkPieceMoveRange(final Board board, final Position from, final Position to) {
        if (from.getFile().equals(to.getFile()) || from.getRankNumber() == to.getRankNumber()) {
            checkAnyPiece(board, from, to);
            return;
        }
        if (File.difference(from.getFile(), to.getFile()) != Rank.difference(from.getRankNumber(), to.getRankNumber())) {
            throw new IllegalArgumentException("퀸은 상하좌우 대각선 방향으로만 이동할 수 있습니다.");
        }
        board.checkPieceInDiagonal(from, to);
    }

    private void checkAnyPiece(final Board board, final Position from, final Position to) {
        if (board.hasPieceInFile(from, to) || board.hasPieceInRank(from, to)) {
            throw new IllegalArgumentException("이동 경로에 기물이 존재합니다.");
        }
    }
}
