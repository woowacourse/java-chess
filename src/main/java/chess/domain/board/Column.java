package chess.domain.board;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Column implements Comparable<Column> {
    private static final List<Column> COLUMN_POOL;
    private static final char MIN_COLUMN_SIZE = 'a';
    private static final char MAX_COLUMN_SIZE = 'h';

    static {
        COLUMN_POOL = Collections.unmodifiableList(
                IntStream.rangeClosed(MIN_COLUMN_SIZE, MAX_COLUMN_SIZE)
                        .mapToObj(i -> new Column((char) i))
                        .collect(Collectors.toList()));
    }

    private final char column;

    private Column(char column) {
        this.column = column;
    }

    public static Column of(char column) {
        if (column > MAX_COLUMN_SIZE || column < MIN_COLUMN_SIZE) {
            throw new InvalidColumnException("Column의 범위에 벗어납니다.");
        }
        return COLUMN_POOL.get(column - MIN_COLUMN_SIZE);
    }

    static Column of(String columnText) {
        if (StringUtils.isEmpty(columnText)) {
            throw new InvalidColumnException("불가능한 Column 형식입니다.");
        }
        return of(columnText.toLowerCase().charAt(0));
    }

    public static Stream<Column> stream() {
        return COLUMN_POOL.stream();
    }

    @Override
    public String toString() {
        return "Column{" +
                "column=" + column +
                '}';
    }

    @Override
    public int compareTo(Column o) {
        return this.column - o.column;
    }

    public int getDiff(Column column) {
        return this.column - column.column;
    }

    public Column next(int columnDiff) {
        return of((char) (column + columnDiff));
    }
}
