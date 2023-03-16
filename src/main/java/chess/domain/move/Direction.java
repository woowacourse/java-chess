package chess.domain.move;

import java.util.ArrayList;
import java.util.List;

public enum Direction {
    RIGHT {
        @Override
        public Direction flipVertical() {
            return LEFT;
        }
    },
    UP {
        @Override
        public Direction flipHorizontal() {
            return DOWN;
        }
    },
    LEFT {
        @Override
        public Direction flipVertical() {
            return RIGHT;
        }
    },
    DOWN {
        @Override
        public Direction flipHorizontal() {
            return UP;
        }
    };

    public static List<Direction> from(int deltaFile, int deltaRank) {
        List<Direction> directions = new ArrayList<>();
        addHorizontalDirections(deltaFile, directions);
        addVerticalDirections(deltaRank, directions);
        return directions;
    }

    private static void addHorizontalDirections(int deltaFile, List<Direction> directions) {
        while (deltaFile > 0) {
            directions.add(RIGHT);
            deltaFile--;
        }
        while (deltaFile < 0) {
            directions.add(LEFT);
            deltaFile++;
        }
    }

    private static void addVerticalDirections(int deltaRank, List<Direction> directions) {
        while (deltaRank > 0) {
            directions.add(UP);
            deltaRank--;
        }
        while (deltaRank < 0) {
            directions.add(DOWN);
            deltaRank++;
        }
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
