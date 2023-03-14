package chess.domain;

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

    public Direction flipHorizontal() {
        return this;
    }

    public Direction flipVertical() {
        return this;
    }
}
