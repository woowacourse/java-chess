package chess.domain.move.enums;

public enum DiagonalMove implements MoveEnum {

    RIGHT_UP(1, 1) {
        @Override
        public boolean isMove(final int dx, final int dy) {
            return Math.abs(dx) == Math.abs(dy) && dx > 0 && dy > 0;
        }
    },
    LEFT_UP(-1, 1) {
        @Override
        public boolean isMove(final int dx, final int dy) {
            return Math.abs(dx) == Math.abs(dy) && dx < 0 && dy > 0;
        }
    },
    RIGHT_DOWN(1, -1) {
        @Override
        public boolean isMove(final int dx, final int dy) {
            return Math.abs(dx) == Math.abs(dy) && dx > 0 && dy < 0;
        }
    },
    LEFT_DOWN(-1, -1) {
        @Override
        public boolean isMove(final int dx, final int dy) {
            return Math.abs(dx) == Math.abs(dy) && dx < 0 && dy < 0;
        }
    };

    private final int dx;
    private final int dy;

    DiagonalMove(final int dx, final int dy) {
        this.dx = dx;
        this.dy = dy;
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
