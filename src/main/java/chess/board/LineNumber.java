package chess.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LineNumber {

    public static final int MIN = 1;
    public static final int MAX = 8;
    private static final Map<Integer, LineNumber> LINE_NUMBER_CACHE = new HashMap<>();

    private final int number;

    private LineNumber(int number) {
        this.number = number;
    }

    public static LineNumber of(String input) {
        return of(Integer.parseInt(input));
    }

    public static LineNumber of(int number) {
        validateRange(number);
        return LINE_NUMBER_CACHE.computeIfAbsent(number, ignored -> new LineNumber(number));
    }

    private static void validateRange(int number) {
        if (number < MIN || MAX < number) {
            throw new IllegalArgumentException(String.format("[ERROR] 위치는 %d ~ %d 까지 입력 가능합니다.", MIN, MAX));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineNumber that = (LineNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
