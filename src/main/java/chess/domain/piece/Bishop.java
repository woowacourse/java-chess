package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class Bishop extends Piece {

    private static final double SCORE = 3;

    public Bishop(final Color color) {
        super(color);
    }

    public static boolean isBishopMoving(final Position from, final Position to) {
        return Direction.isDiagonal(from, to);
    }

    @Override
    public void checkMovingRange(final Board board, final Position from, final Position to) {
        if (!isBishopMoving(from, to)) {
            throw new IllegalArgumentException("비숍은 대각선 방향만 이동할 수 있습니다.");
        }
        if (board.hasPieceInDiagonal(from, to)) {
            throw new IllegalArgumentException("이동 경로에 기물이 존재합니다.");
        }
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
