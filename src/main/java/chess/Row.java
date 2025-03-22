package chess;

public enum Row {

    EIGHT,
    SEVEN,
    SIX,
    FIVE,
    FOUR,
    THREE,
    TWO,
    ONE;

    public static Row from(String s) {
        return switch (s) {
            case "1" -> ONE;
            case "2" -> TWO;
            case "3" -> THREE;
            case "4" -> FOUR;
            case "5" -> FIVE;
            case "6" -> SIX;
            case "7" -> SEVEN;
            case "8" -> EIGHT;
            default -> throw new IllegalArgumentException("없는 Row입니다. 1부터 8까지의 숫자만 입력하세요.");
        };
    }

    public static int getDifference(Row row1, Row row2) {
        Row maxRow = getMaxOf(row1, row2);
        Row minRow = getMinOf(row1, row2);

        return maxRow.toInt() - minRow.toInt();
    }

    private static Row getMaxOf(Row row1, Row row2) {
        if (row1.toInt() > row2.toInt()) {
            return row1;
        }
        return row2;
    }

    private static Row getMinOf(Row row1, Row row2) {
        if (row1.toInt() > row2.toInt()) {
            return row2;
        }
        return row1;
    }

    public int toInt() {
        if (this == Row.ONE) return 1;
        if (this == Row.TWO) return 2;
        if (this == Row.THREE) return 3;
        if (this == Row.FOUR) return 4;
        if (this == Row.FIVE) return 5;
        if (this == Row.SIX) return 6;
        if (this == Row.SEVEN) return 7;
        if (this == Row.EIGHT) return 8;
        throw new IllegalArgumentException("Unknown row: " + this);
    }


    public boolean isTop() {
        return ordinal() == 0;
    }

    public boolean isBottom() {
        return ordinal() + 1 == values().length;
    }

    public boolean canMoveUp(final int step) {
        return ordinal() - step >= 0;
    }

    public Row moveUp() {
        return moveUp(1);
    }

    public Row moveUp(final int step) {
        if (canMoveUp(step)) {
            return values()[ordinal() - step];
        }

        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }

    public boolean canMoveDown(final int step) {
        return ordinal() + step < values().length;
    }

    public Row moveDown() {
        return moveDown(1);
    }

    public Row moveDown(final int step) {
        if (canMoveDown(step)) {
            return values()[ordinal() + step];
        }

        throw new IllegalStateException("움직일 수 없는 위치입니다.");
    }
}
