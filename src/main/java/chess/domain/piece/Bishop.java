package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.Objects;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        super(color);
    }

    private static boolean isBishopMoving(final Position from, final Position to) {
        return Direction.isDiagonal(from, to);
    }

    @Override
    public void checkMovingRange(final Board board, final Position from, final Position to) {
        if (!isBishopMoving(from, to)) {
            throw new IllegalArgumentException("비숍은 대각선 방향만 이동할 수 있습니다.");
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
        if (!(other instanceof Bishop)) return false;

        Bishop piece = (Bishop) other;

        return getColor() == piece.getColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor());
    }
}
