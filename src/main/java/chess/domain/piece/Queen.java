package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class Queen extends Piece {

    private static final double SCORE = 9;

    public Queen(final Color color) {
        super(color);
    }

    public static boolean isQueenMoving(final Position from, final Position to) {
        return Direction.isVertical(from, to) || Direction.isHorizontal(from, to) || Direction.isDiagonal(from, to);
    }

    private boolean hasAnyPiece(final Board board, final Position from, final Position to) {
        return board.hasPieceInXAxis(from, to) || board.hasPieceInYAxis(from, to) || board.hasPieceInDiagonal(from, to);
    }

    @Override
    public void checkMovingRange(final Board board, final Position from, final Position to) {
        if (!isQueenMoving(from, to)) {
            throw new IllegalArgumentException("퀸은 상하좌우 대각선 방향으로만 이동할 수 있습니다.");
        }
        if (hasAnyPiece(board, from, to)) {
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
