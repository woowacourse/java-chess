package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class Rook extends Piece {

    private static final String NAME = "r";
    private static final double SCORE = 5;

    public Rook(final Color color) {
        super(color, NAME);
    }

    public static boolean isRookMoving(final Position from, final Position to) {
        return Direction.isVertical(from, to) || Direction.isHorizontal(from, to);
    }

    private boolean hasAnyPiece(final Board board, final Position from, final Position to) {
        return board.hasPieceInXAxis(from, to) || board.hasPieceInYAxis(from, to);
    }

    @Override
    public void checkMovingRange(final Board board, final Position from, final Position to) {
        if (!isRookMoving(from, to)) {
            throw new IllegalArgumentException("룩은 대각선으로 이동할 수 없습니다.");
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
