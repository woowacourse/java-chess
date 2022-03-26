package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class King extends Piece {

    private static final String NAME = "k";

    public King(final Color color) {
        super(color, NAME);
    }

    @Override
    public void checkPieceMoveRange(final Board board, final Position from, final Position to) {
        if (File.difference(from.getFile(), to.getFile()) == 0 && Rank.difference(from.getRankNumber(), to.getRankNumber()) == 1) {
            return;
        }
        if (Rank.difference(from.getRankNumber(), to.getRankNumber()) == 0 && File.difference(from.getFile(), to.getFile()) == 1) {
            return;
        }
        if (File.difference(from.getFile(), to.getFile()) == 1 && Rank.difference(from.getRankNumber(), to.getRankNumber()) == 1) {
            return;
        }
        throw new IllegalArgumentException("킹은 모든 방향으로 한 칸 이동 가능합니다.");
    }
}
