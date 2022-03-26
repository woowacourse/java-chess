package chess.domain.piece;

import chess.domain.Board;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class Bishop extends Piece {

    private static final String NAME = "b";

    public Bishop(final Color color) {
        super(color, NAME);
    }

    @Override
    public void checkPieceMoveRange(final Board board, final Position from, final Position to) {
        if (File.difference(from.getFile(), to.getFile()) != Rank.difference(from.getRankNumber(), to.getRankNumber())) {
            throw new IllegalArgumentException("비숍은 대각선 방향만 이동할 수 있습니다.");
        }
//        if (board.hasPieceInDiagonal(from, to)) {
//            throw new IllegalArgumentException("이동 경로에 기물이 존재합니다.");
//        }
        board.checkPieceInDiagonal(from, to);
    }
}
