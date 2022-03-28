package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class King extends Piece {

    private static final String NAME = "k";
    private static final double SCORE = 0;

    public King(final Color color) {
        super(color, NAME, SCORE);
    }

    public static boolean isKingMoving(final Position from, final Position to) {
        Direction direction = Direction.getDirection(from, to);

        return Direction.kingStep().contains(direction);
    }

    @Override
    public void checkMovingRange(final Board board, final Position from, final Position to) {
        if (isKingMoving(from, to)) {
            return;
        }
        throw new IllegalArgumentException("킹은 모든 방향으로 한 칸 이동 가능합니다.");
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
