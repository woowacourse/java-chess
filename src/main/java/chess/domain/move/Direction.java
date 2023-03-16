package chess.domain.move;

import java.util.ArrayList;
import java.util.List;

public enum Direction {
    RIGHT(1, 0) {
        @Override
        public Direction flipVertical() {
            return LEFT;
        }
    },
    UP(0, 1) {
        @Override
        public Direction flipHorizontal() {
            return DOWN;
        }
    },
    LEFT(-1, 0) {
        @Override
        public Direction flipVertical() {
            return RIGHT;
        }
    },
    DOWN(0, -1) {
        @Override
        public Direction flipHorizontal() {
            return UP;
        }
    };

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static List<Direction> from(int deltaFile, int deltaRank) {
        List<Direction> directions = new ArrayList<>();
        while (deltaFile > 0) {
            directions.add(RIGHT);
            deltaFile--;
        }
        while (deltaFile < 0) {
            directions.add(LEFT);
            deltaFile++;
        }
        while (deltaRank > 0) {
            directions.add(UP);
            deltaRank--;
        }
        while (deltaRank < 0) {
            directions.add(DOWN);
            deltaRank++;
        }
        return directions;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public boolean isHorizontal() {
        return List.of(RIGHT, LEFT).contains(this);
    }

    public boolean isVertical() {
        return !isHorizontal();
    }

    public Direction flipHorizontal() {
        return this;
    }

    public Direction flipVertical() {
        return this;
    }
}
