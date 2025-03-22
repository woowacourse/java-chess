package chess.position;

public record Offset(int x, int y) {

    public static Offset calculate(
            final Position before,
            final Position after
    ) {
        int x = before.column().offset(after.column());
        int y = before.row().offset(after.row());

        return new Offset(x, y);
    }
}
