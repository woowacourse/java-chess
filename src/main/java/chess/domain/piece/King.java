package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

public class King extends Piece {

    private static final String NAME = "k";

    public King(final Color color) {
        super(color, NAME);
    }

    @Override
    public void checkPieceMoveRange(final Board board, final Position from, final Position to) {
        if (isBothSidesOneStep(from, to) || isUpAndDownOneStep(from, to) || isDiagonalOneStep(from, to)) {
            return;
        }
        throw new IllegalArgumentException("킹은 모든 방향으로 한 칸 이동 가능합니다.");
    }
}
