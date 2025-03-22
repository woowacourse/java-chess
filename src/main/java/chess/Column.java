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

    public static Column change(char value) {
        if (value == 'A') {
            return A;
        }

        if (value == 'B') {
            return B;
        }

        if (value == 'C') {
            return C;
        }

        if (value == 'D') {
            return D;
        }

        if (value == 'E') {
            return E;
        }

        if (value == 'F') {
            return F;
        }

        if (value == 'G') {
            return G;
        }

        if (value == 'H') {
            return H;
        }

        throw new IllegalArgumentException("[ERROR] 그런 열은 없어용");
    }

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
}
