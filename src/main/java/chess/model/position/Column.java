package chess.model.position;

import java.util.Arrays;

public enum Column {

    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H;

    public boolean isFarLeft() {
        return ordinal() == 0;
    }

    public boolean isFarRight() {
        return ordinal() + 1 == values().length;
    }

    public boolean canMoveLeft(final int step) {
        return ordinal() - step >= 0;
    }

    public Column moveLeft() {
        return moveLeft(1);
    }

    public Column moveLeft(final int step) {
        if (canMoveLeft(step)) {
            return values()[ordinal() - step];
        }

        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }

    public boolean canMoveRight(final int step) {
        return ordinal() + step < values().length;
    }

    public Column moveRight() {
        return moveRight(1);
    }

    public Column moveRight(final int step) {
        if (canMoveRight(step)) {
            return values()[ordinal() + step];
        }

        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }

    public static Column findByNumber(int number) {
        return Arrays.stream(Column.values())
                .filter(o -> o.ordinal() == 8 - number)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 number로 Column를 찾으려 하고 있습니다"));
    }
}
