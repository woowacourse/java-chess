package chess.domain.position;

import chess.domain.piece.strategy.Direction;
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
        if (isBoundary(direction)) {
            return this;
        }
        return getColumn(value + direction.getCoordinates().get(0));
    }

    public String getName() {
        return name;
    }

    public boolean isBoundary(Direction direction) {
        if (Direction.RIGHT.equals(direction) || Direction.UP_RIGHT.equals(direction)
            || Direction.DOWN_RIGHT.equals(direction) || Direction.R_DD.equals(direction)
            || Direction.R_UU.equals(direction)) {
            return this.equals(H);
        }
        if (Direction.RR_D.equals(direction) || Direction.RR_U.equals(direction)) {
            return this.equals(G) || this.equals(H);
        }
        if (Direction.LL_D.equals(direction) || Direction.LL_U.equals(direction)) {
            return this.equals(B) || this.equals(A);
        }
        return this.equals(A);
    }

    public int getValue() {
        return value;
    }
}
