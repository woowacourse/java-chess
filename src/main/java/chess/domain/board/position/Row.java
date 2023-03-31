package chess.domain.board.position;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
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

    private static final char STARTING_CHARACTER_OF_ROW = '0';

    private final int value;

    Row(final int value) {
        this.value = value;
    }

    public static Row from(int value) {
        return Arrays.stream(values())
                     .filter(it -> it.value == value)
                     .findAny()
                     .orElseThrow(() -> new NoSuchElementException("이동할 수 없는 row 방향입니다."));
    }

    public static Row from(char value) {
        return Arrays.stream(values())
                     .filter(it -> it.value == value - STARTING_CHARACTER_OF_ROW)
                     .findAny()
                     .orElseThrow(() -> new NoSuchElementException("이동할 수 없는 row 방향입니다."));
    }

    public static List<Integer> findPossibleRowCandidates() {
        return Arrays.stream(values())
                     .map(it -> it.value)
                     .collect(Collectors.toList());
    }

    public int differenceBetween(Row other) {
        return value - other.value;
    }

    public int value() {
        return value;
    }
}
