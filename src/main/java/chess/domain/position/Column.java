package chess.domain.position;

import chess.domain.piece.Direction;
import java.util.Arrays;

public enum Column {
    A(1, "a"),
    B(2, "b"),
    C(3, "c"),
    D(4, "d"),
    E(5, "e"),
    F(6, "f"),
    G(7, "g"),
    H(8, "h");

    private final int value;
    private final String name;

    Column(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Column getColumn(String value) {
        return Arrays.stream(values())
            .filter(column -> column.name.equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 열입니다"));
    }

    public static Column getColumn(int intValue) {
        return Arrays.stream(values())
            .filter(column -> column.value == intValue)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 열입니다"));
    }

    public Column move(Direction direction) {
        return getColumn(value + direction.columnValue());
    }

    public boolean isInRange(int moveValue) {
        return Arrays.stream(values())
            .anyMatch(column -> column.value == this.value + moveValue);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
