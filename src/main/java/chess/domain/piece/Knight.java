package chess.domain.piece;

import chess.domain.Board;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class Knight extends Piece {

    private static final String NAME = "n";
    private static final int FIRST_STEP = 2;
    private static final int SECOND_STEP = 1;

    public Knight(final Color color) {
        super(color, NAME);
    }

    @Override
    public void checkPieceMoveRange(final Board board, final Position from, final Position to) {
        if (Rank.difference(from.getRankNumber(), to.getRankNumber()) == FIRST_STEP && File.difference(from.getFile(), to.getFile()) == SECOND_STEP) {
            return;
        }
        if (File.difference(from.getFile(), to.getFile()) == FIRST_STEP && Rank.difference(from.getRankNumber(), to.getRankNumber()) == SECOND_STEP) {
            return;
        }
        throw new IllegalArgumentException("나이트는 두 칸 이동 후 90도 방향으로 한 칸 이동할 수 있습니다.");
    }
}
