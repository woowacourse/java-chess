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

    public static Column from(String s) {
        for (Column value : Column.values()) {
            if (value.toString().toLowerCase().equals(s.toLowerCase())) {
                return value;
            }
        }

        throw new IllegalArgumentException("입력이 잘못되었습니다. ABCDEFGH 사이의 값만 입력해주세요");
    }

    public static int getDifference(Column column1, Column column2) {
        return Math.abs(toInt(column1) - toInt(column2));
    }

    private static int toInt(Column column) {
        return switch (column) {
            case A -> 1;
            case B -> 2;
            case C -> 3;
            case D -> 4;
            case E -> 5;
            case F -> 6;
            case G -> 7;
            case H -> 8;
            default -> throw new IllegalArgumentException("Unknown column: " + column);
        };
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
