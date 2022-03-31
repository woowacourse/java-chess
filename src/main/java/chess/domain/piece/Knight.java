package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.Objects;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(color);
    }

    private static boolean isKnightMoving(final Position from, final Position to) {
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
    public boolean isKnight() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Knight)) return false;

        Knight piece = (Knight) other;

        return getColor() == piece.getColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor());
    }
}
