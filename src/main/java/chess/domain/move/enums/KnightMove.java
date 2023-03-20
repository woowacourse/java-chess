package chess.domain.move.enums;

public enum KnightMove implements MoveEnum {

    LEFT_UP_UP(-1, 2) {
        @Override
        public boolean isMove(final int dx, final int dy) {
            return isKnightMove(dx, dy) && dx == -1 && dy == 2;
        }
    },
    RIGHT_UP_UP(1, 2) {
        @Override
        public boolean isMove(final int dx, final int dy) {
            return isKnightMove(dx, dy) && dx == 1 && dy == 2;
        }
    },
    RIGHT_RIGHT_UP(2, 1) {
        @Override
        public boolean isMove(final int dx, final int dy) {
            return isKnightMove(dx, dy) && dx == 2 && dy == 1;
        }
    },
    RIGHT_RIGHT_DOWN(2, -1) {
        @Override
        public boolean isMove(final int dx, final int dy) {
            return isKnightMove(dx, dy) && dx == 2 && dy == -1;
        }
    },
    RIGHT_DOWN_DOWN(1, -2) {
        @Override
        public boolean isMove(final int dx, final int dy) {
            return isKnightMove(dx, dy) && dx == 1 && dy == -2;
        }
    },
    LEFT_DOWN_DOWN(-1, -2) {
        @Override
        public boolean isMove(final int dx, final int dy) {
            return isKnightMove(dx, dy) && dx == -1 && dy == -2;
        }
    },
    LEFT_LEFT_DOWN(-2, -1) {
        @Override
        public boolean isMove(final int dx, final int dy) {
            return isKnightMove(dx, dy) && dx == -2 && dy == -1;
        }
    },
    LEFT_LEFT_UP(-2, 1) {
        @Override
        public boolean isMove(final int dx, final int dy) {
            return isKnightMove(dx, dy) && dx == -2 && dy == 1;
        }
    };

    private final int dx;

    private final int dy;
    KnightMove(final int dx, final int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    private static boolean isKnightMove(final int dx, final int dy) {
        return Math.abs(dx * dy) == 2;
    }

    @Override
    public int getDx() {
        return dx;
    }

    @Override
    public int getDy() {
        return dy;
    }
}
