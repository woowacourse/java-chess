package chess.domain;

public enum Direction {
    RIGHT,
    UP {
        @Override
        public Direction flipHorizontal() {
            return DOWN;
        }
    },
    LEFT,
    DOWN {
        @Override
        public Direction flipHorizontal() {
            return UP;
        }
    };

    public Direction flipHorizontal() {
        return this;
    }
}
