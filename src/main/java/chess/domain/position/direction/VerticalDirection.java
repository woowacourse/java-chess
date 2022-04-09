package chess.domain.position.direction;

import chess.domain.position.Position;

public class VerticalDirection implements Direction{
    @Override
    public boolean isOnDirection(Position from, Position to) {
        return isOnVertical(from, to);
    }

    public boolean isOnVertical(Position from, Position to) {
        return from.isSameXAxis(to);
    }

    public static boolean isInVerticalRange(Position from, Position other, int range) {
        return Math.abs(from.getYAxis().subtract(other.getYAxis())) <= range;
    }
}
