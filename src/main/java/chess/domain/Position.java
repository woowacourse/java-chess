package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {
    private static final int MIN_VER_INT = 1;
    private static final int MAX_VER_INT = 8;
    private static final char MIN_VER_CHAR = 'a';
    private static final char MAX_VER_CHAR = 'h';
    private static final int CHAR_DEFORMATION_VALUE = 96;

    private final int x;
    private final int y;

    public static Position of(int x, int y) {
        validateIntRange(x);
        validateIntRange(y);
        return new Position(x, y);
    }

    public static Position of(int x, char y) {
        validateIntRange(x);
        validateCharRange(y);
        return new Position(x, y - CHAR_DEFORMATION_VALUE);
    }

    private static void validateIntRange(int num) {
        if (num < MIN_VER_INT || num > MAX_VER_INT) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateCharRange(char num) {
        if (num < MIN_VER_CHAR || num > MAX_VER_CHAR) {
            throw new IllegalArgumentException();
        }
    }

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public PositionGap calculateDiff(Position targetPosition) {
        return new PositionGap(targetPosition.x - this.x, targetPosition.y - this.y);
    }

    public Position plus(Direction direction) {
        return new Position(direction.plusX(x), direction.plusY(y));
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Position position = (Position)o;
        return x == position.x &&
            y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public List<Position> calculatePath(Position targetPosition, Direction direction) {
        int pathX = direction.plusX(x);
        int pathY = direction.plusY(y);
        List<Position> positions = new ArrayList<>();
        while (new Position(pathX, pathY).isNotEquals(targetPosition)) {
            positions.add(new Position(pathX, pathY));
            pathX = direction.plusX(pathX);
            pathY = direction.plusY(pathY);
        }
        return positions;
    }

    private boolean isNotEquals(Position targetPosition) {
        return this.equals(targetPosition) == false;
    }
}
