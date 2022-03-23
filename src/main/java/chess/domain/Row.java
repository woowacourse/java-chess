package chess.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum Row {

    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8");

    private final String value;

    Row(String value) {
        this.value = value;
    }

    public static Row of(String targetValue) {
        return Arrays.stream(values())
                .filter(row -> row.value.equals(targetValue))
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
}
