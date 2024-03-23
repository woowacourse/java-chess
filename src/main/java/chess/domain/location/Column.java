package chess.domain.location;

import chess.domain.board.Direction;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Column {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private static final Map<String, Column> CACHED_COLUMNS_BY_NAME =
            Arrays.stream(values())
                    .collect(Collectors.toMap(Enum::name, Function.identity()));

    private final int index;

    Column(int index) {
        this.index = index;
    }

    public static Column createByName(String inputName) {
        return Optional.ofNullable(CACHED_COLUMNS_BY_NAME.get(inputName.toUpperCase()))
                .orElseThrow(() -> new IllegalArgumentException("잘못된 Column 입력입니다."));
    }

    public static Column createByIndex(int inputIndex) {
        return Arrays.stream(values())
                .filter(column -> column.isIndexOf(inputIndex))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 방향 입력입니다."));
    }

    public Column move(Direction direction) {
        if (direction.isLeftSide()) {
            return this.previous();
        }
        if (direction.isRightSide()) {
            return this.next();
        }
        return this;
    }

    private Column previous() {
        return Column.createByIndex(this.index - 1);
    }

    private Column next() {
        return Column.createByIndex(this.index + 1);
    }

    public int calculateDistance(Column other) {
        return other.index - this.index;
    }

    public boolean isIndexOf(int index) {
        return this.index == index;
    }
}
