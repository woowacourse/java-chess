package chess.position;

public record Offset(int x, int y) {

    public Offset minus(final Movement movement) {
        return new Offset(this.x - movement.x(), this.y - movement.y());
    }

    public boolean is00() {
        return x == 0 && y == 0;
    }

    public boolean isDiagonal() {
        return x != 0 && y != 0 && Math.abs(x) == Math.abs(y);
    }

    public static Offset calculate(
            final Position before,
            final Position after
    ) {
        int x = before.column().offset(after.column());
        int y = before.row().offset(after.row());

        return new Offset(x, y);
    }
}
