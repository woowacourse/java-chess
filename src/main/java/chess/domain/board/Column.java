package chess.domain.board;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Column {

    A('a'),
    B('b'),
    C('c'),
    D('d'),
    E('e'),
    F('f'),
    G('g'),
    H('h');

    private final char value;

    Column(final char value) {
        this.value = value;
    }

    private static Map<Character, Column> CACHE =
            Arrays.stream(values()).collect(Collectors.toMap(Column::getValue, Function.identity()));

    private static Column of(final char value) {
        return Optional
                .ofNullable(CACHE.get(value))
                .orElseThrow(() -> new IllegalStateException("[ERROR] 존재하지 않는 Column 입니다."));
    }

    public Column move(final int horizon) {
        return of((char) (this.value + horizon));
    }

    public int subtractValue(final Column column) {
        return column.value - this.value;
    }

    public char getValue() {
        return value;
    }
}
