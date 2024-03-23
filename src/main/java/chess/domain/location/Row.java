package chess.domain.location;

import chess.domain.board.Direction;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Row {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    public static final Map<Integer, Row> ROWS =
            Arrays.stream(values())
                    .collect(Collectors.toMap(Row::getRank, Function.identity()));

    private final int rank;

    Row(int rank) {
        this.rank = rank;
    }

    public static Row createByRank(String rank) {
        try {
            return createByRank(Integer.parseInt(rank));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 Row 입력입니다. 숫자를 입력해 주세요");
        }
    }

    public static Row createByRank(int rank) {
        return Optional.ofNullable(ROWS.get(rank))
                .orElseThrow(() -> new IllegalArgumentException("잘못된 Row 입력입니다."));
    }

    public Row move(Direction direction) {
        if (direction.isUpSide()) {
            return this.next();
        }
        if (direction.isDownside()) {
            return this.previous();
        }
        return this;
    }

    private Row previous() {
        return Row.createByRank(this.rank - 1);
    }

    private Row next() {
        return Row.createByRank(this.rank + 1);
    }

    public int calculateDistance(Row other) {
        return other.rank - this.rank;
    }

    private int getRank() {
        return rank;
    }
}
