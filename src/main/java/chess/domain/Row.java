package chess.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Row implements Comparable<Row> {
    private static final List<Row> ROW_POOL;
    private static final int MIN_ROW_SIZE = 1;
    private static final int MAX_ROW_SIZE = 8;

    static {
        ROW_POOL = Collections.unmodifiableList(
                IntStream.rangeClosed(MIN_ROW_SIZE, MAX_ROW_SIZE)
                        .mapToObj(Row::new)
                        .collect(Collectors.toList()));
    }

    private final int row;

    private Row(int row) {
        this.row = row;
    }

    public static Row of(int row) {
        if (row > MAX_ROW_SIZE || row < MIN_ROW_SIZE) {
            throw new InvalidRowException("Row 범위에 벗어납니다.");
        }
        return ROW_POOL.get(row - 1);
    }

    static Row of(String row) {
        if (StringUtils.isEmpty(row)) {
            throw new InvalidRowException("Row는 숫자 형식입니다.");
        }

        try {
            return of(Integer.parseInt(row));
        } catch (NumberFormatException e) {
            throw new InvalidRowException("Row는 숫자 형식입니다.");
        }
    }

    public static Stream<Row> stream() {
        return ROW_POOL.stream();
    }

    @Override
    public String toString() {
        return "Row{" +
                "row=" + row +
                '}';
    }

    @Override
    public int compareTo(Row o) {
        return row - o.row;
    }
}
