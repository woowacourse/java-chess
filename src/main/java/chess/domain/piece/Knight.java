package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class Knight extends Piece {

    private static final double SCORE = 0;

    public Knight(final Color color) {
        super(color);
    }

    public static boolean isKnightMoving(final Position from, final Position to) {
        Direction direction = Direction.getDirection(from, to);

        return Direction.knightStep().contains(direction);
    }

    @Override
    public void checkMovingRange(final Board board, final Position from, final Position to) {
        if (isKnightMoving(from, to)) {
            return;
        }
        throw new IllegalArgumentException("나이트는 두 칸 이동 후 90도 방향으로 한 칸 이동할 수 있습니다.");
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
