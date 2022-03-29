package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class Rook extends Piece {

    private static final double SCORE = 5;

    public Rook(final Color color) {
        super(color);
    }

    public static boolean isRookMoving(final Position from, final Position to) {
        return Direction.isVertical(from, to) || Direction.isHorizontal(from, to);
    }

    @Override
    public void checkMovingRange(final Board board, final Position from, final Position to) {
        if (!isRookMoving(from, to)) {
            throw new IllegalArgumentException("룩은 대각선으로 이동할 수 없습니다.");
        }
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKnight() {
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
