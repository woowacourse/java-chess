package chess.domain.board.position;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public enum Column {

    A(1),
    B(2),
    C(3),
    D(4),
    E(5),
    F(6),
    G(7),
    H(8),

    START(1),
    END(8);

    private static final char STARTING_CHARACTER_OF_COLUMN = 'a';

    private final int value;

    Column(final int value) {
        this.value = value;
    }

    public static Column from(int value) {
        return Arrays.stream(values())
                     .filter(it -> it.value == value)
                     .findAny()
                     .orElseThrow(() -> new NoSuchElementException("이동할 수 없는 column 방향입니다."));
    }

    public static Column from(char value) {
        return Arrays.stream(values())
                     .filter(it -> it.value == value - STARTING_CHARACTER_OF_COLUMN + START.value)
                     .findAny()
                     .orElseThrow(() -> new NoSuchElementException("이동할 수 없는 column 방향입니다."));
    }

    public static List<String> findPossibleColumnCandidates() {
        return Arrays.stream(values())
                     .filter(it -> it.name().length() == 1)
                     .map(it -> it.name().toLowerCase())
                     .collect(Collectors.toList());
    }

    public int differenceBetween(Column other) {
        return value - other.value;
    }

    public int value() {
        return value;
    }
}
