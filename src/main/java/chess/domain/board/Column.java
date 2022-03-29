package chess.domain.board;

import java.util.Arrays;

public enum Column {

    a,
    b,
    c,
    d,
    e,
    f,
    g,
    h;

    public static Column from(final int value) {
        return Arrays.stream(values())
                .filter(column -> column.ordinal() == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 위치입니다."));
    }

    public static Column from(final String position) {
        if (position.length() != 1) {
            throw new IllegalArgumentException("올바른 세로 위치가 아닙니다.");
        }
        return from(position.charAt(0) - 'a');
    }

    public int calculateDifference(final Column target) {
        return this.ordinal() - target.ordinal();
    }

    public Column move(final int columnDifference) {
        return from(ordinal() + columnDifference);
    }
}
