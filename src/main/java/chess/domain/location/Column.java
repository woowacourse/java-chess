package chess.domain.location;

import chess.domain.board.Direction;
import java.util.List;

public enum Column {
    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8);

    private static final List<Column> COLUMNS = List.of(A, B, C, D, E, F, G, H);
    private final int index;

    Column(int index) {
        this.index = index;
    }

    public static Column of(String input) {
        return COLUMNS.stream()
                .filter(column -> column.equalName(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 Column 입력입니다."));
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
        int ordinalIndex = this.index - 1;
        return moveByIndex(ordinalIndex - 1);
    }

    private Column next() {
        int ordinalIndex = this.index - 1;
        return moveByIndex(ordinalIndex + 1);
    }

    private Column moveByIndex(int ordinalIndex) {
        try {
            return COLUMNS.get(ordinalIndex);
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("잘못된 방향 입력입니다.");
        }
    }

    public int calculateDistance(Column other) {
        return other.index - this.index;
    }

    private boolean equalName(String name) {
        return this.name().equalsIgnoreCase(name);
    }
}
