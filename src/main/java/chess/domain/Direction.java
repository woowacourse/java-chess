package chess.domain;

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
