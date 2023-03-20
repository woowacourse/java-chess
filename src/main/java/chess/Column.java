package chess;

public enum Column {

    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H;

    public boolean canMove(final int step) {
        final var movedIndex = ordinal() + step;
        return values().length > movedIndex && movedIndex >= 0;
    }

    public Column move(final int step) {
        if (canMove(step)) {
            return values()[ordinal() + step];
        }

        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }
}
