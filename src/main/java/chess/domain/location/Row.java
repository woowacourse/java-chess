package chess.domain.location;

import chess.domain.board.Direction;
import java.util.Arrays;
import java.util.List;

public enum Row {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private static final List<Row> ROWS = List.of(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT);
    private final int rank;

    Row(int rank) {
        this.rank = rank;
    }

    public static Row of(String input) {
        return Arrays.stream(values())
                .filter(row -> row.isRank(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 Row 입력입니다."));
    }

    private boolean isRank(String input) {
        return rank == Integer.parseInt(input);
    }

    public Row move(Direction direction) {
        if (direction.isUpside()) {
            return this.next();
        }
        if (direction.isDownside()) {
            return this.previous();
        }
        return this;
    }

    private Row previous() {
        int ordinalIndex = this.rank - 1;
        return moveByIndex(ordinalIndex - 1);
    }

    private Row next() {
        int ordinalIndex = this.rank - 1;
        return moveByIndex(ordinalIndex + 1);
    }

    private Row moveByIndex(int ordinalIndex) {
        try {
            return ROWS.get(ordinalIndex);
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalArgumentException("잘못된 방향 입력입니다.");
        }
    }

    public int calculateDistance(Row other) {
        return other.rank - this.rank;
    }
}
