package chess;

public enum Row {
    
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT;

    public boolean canMove(final int step) {
        final var movedIndex = ordinal() + step;
        return values().length > movedIndex && movedIndex >= 0;
    }

    public Row move(final int step) {
        if (canMove(step)) {
            return values()[ordinal() + step];
        }

        throw new IllegalStateException("움직일 수 없는 위치입니다. ");
    }
}
