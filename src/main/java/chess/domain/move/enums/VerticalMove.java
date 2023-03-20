package chess.domain.move.enums;

public enum VerticalMove implements MoveEnum {
    UP(0, 1) {
        @Override
        public boolean isMove(final int dx, final int dy) {
            return dx == 0 && dy >= 0;
        }
    },
    DOWN(0, -1) {
        @Override
        public boolean isMove(final int dx, final int dy) {
            return dx == 0 && dy < 0;
        }
    };

    private final int dx;
    private final int dy;

    VerticalMove(final int dx, final int dy) {
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
