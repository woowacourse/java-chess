package chess.domain;

import chess.domain.Piece.Distance;

//todo: add validation logic
public class Position {
    private static final int MIN = 1;
    private static final int MAX = 8;

    private final int x;
    private final int y;

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static Position of(int x, int y) {
        validateInRange(x, y);

        return new Position(x,y);
    }

    Distance calculateDistance(Position position) {
        int xDistance = x - position.x;
        int yDistance = y - position.y;
        return new Distance(xDistance, yDistance);
    }

    private static void validateInRange(int x, int y) {
        if (x < MIN || MAX < x) {
            throw new IllegalArgumentException("x의 범위를 벗어납니다.");
        }
        if (y < MIN || MAX < y) {
            throw new IllegalArgumentException("y의 범위를 벗어납니다.");
        }
    }
}
