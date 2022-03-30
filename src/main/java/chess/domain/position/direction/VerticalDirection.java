package chess.domain.position.direction;

import chess.domain.position.Position;

public class VerticalDirection {

    public static boolean isInVerticalRange(Position from, Position other, int range) {
        return Math.abs(from.getYAxis().subtract(other.getYAxis())) <= range;
    }
}
