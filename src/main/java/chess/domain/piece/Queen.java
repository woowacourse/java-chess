package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.Objects;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(color);
    }

    private static boolean isQueenMoving(final Position from, final Position to) {
        return Direction.isVertical(from, to) || Direction.isHorizontal(from, to) || Direction.isDiagonal(from, to);
    }

    @Override
    public void checkMovingRange(final Board board, final Position from, final Position to) {
        if (!isQueenMoving(from, to)) {
            throw new IllegalArgumentException("퀸은 상하좌우 대각선 방향으로만 이동할 수 있습니다.");
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
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Queen)) return false;

        Queen piece = (Queen) other;

        return getColor() == piece.getColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor());
    }
}
