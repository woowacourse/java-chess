package chess.console.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum PositionRange {

    COLUMN_RANGE('a', 'h'),
    ROW_RANGE('1', '8'),
    ;

    private final char allowedMinimum;
    private final char allowedMaximum;

    PositionRange(final char allowedMinimum, final char allowedMaximum) {
        this.allowedMinimum = allowedMinimum;
        this.allowedMaximum = allowedMaximum;
    }

    public boolean isOutOfRange(final char target) {
        return (target < allowedMinimum || allowedMaximum < target);
    }

    public List<Character> getSortedAllValues() {
        return IntStream.rangeClosed(0, allowedMaximum - allowedMinimum)
                .mapToObj(value -> (char) (value + allowedMinimum))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Character> getReverseSortedAllValues() {
        return IntStream.rangeClosed(0, allowedMaximum - allowedMinimum)
                .mapToObj(value -> (char) (allowedMaximum - value))
                .collect(Collectors.toUnmodifiableList());
    }

    public char getAllowedMinimum() {
        return allowedMinimum;
    }

    public char getAllowedMaximum() {
        return allowedMaximum;
    }
}