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

    private static final String INVALID_COLUMN_ERROR_MESSAGE = "존재하지 않는 열입니다";

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
            .orElseThrow(() -> new IllegalArgumentException(INVALID_COLUMN_ERROR_MESSAGE));
    }

    public static Column getColumn(int intValue) {
        return Arrays.stream(values())
            .filter(column -> column.value == intValue)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(INVALID_COLUMN_ERROR_MESSAGE));
    }

    public Column move(Direction direction) {
        if (isBoundary(direction)) {
            return this;
        }
        return getColumn(value + direction.columnValue());
    }

    public String getName() {
        return name;
    }

    public boolean isBoundary(Direction direction) {
        if (Direction.rightDirection().contains(direction)) {
            return this.equals(H);
        }
        if (Direction.leftDirection().contains(direction)) {
            return this.equals(A);
        }
        if (Direction.RR_D.equals(direction) || Direction.RR_U.equals(direction)) {
            return this.equals(G) || this.equals(H);
        }
        if (Direction.LL_D.equals(direction) || Direction.LL_U.equals(direction)) {
            return this.equals(B) || this.equals(A);
        }
        return false;
    }

    public int getValue() {
        return value;
    }
}
