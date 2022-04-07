package chess.domain.position;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

    private static final int MAX_ROW = 8;
    private static final int MIN_ROW = 1;

    private final int value;

    Row(int value) {
        this.value = value;
    }

    public static Row of(int targetValue) {
        return Arrays.stream(values())
                .filter(row -> row.value == targetValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 행 이름이 들어왔습니다."));
    }

    public static List<Row> getRowsReverseOrder() {
        Row[] values = Row.values();
        List<Row> reverse = Arrays.stream(values)
                .collect(Collectors.toList());
        Collections.reverse(reverse);
        return reverse;
    }

    public static int calculateDifference(Row source, Row target) {
        return source.value - target.value;
    }

    public Row move(int value) {
        int indexAfterMove = this.value + value;
        if (indexAfterMove > MAX_ROW || indexAfterMove < MIN_ROW) {
            throw new IndexOutOfBoundsException("범위를 벗어났습니다.");
        }
        return of(indexAfterMove);
    }

    public int getValue() {
        return value;
    }
}
