package chess.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    Column(char value) {
        this.value = value;
    }

    public static Column find(char input) {
        return Arrays.stream(values())
                .filter(i -> i.value == input)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Column 값 입니다."));
    }

    public int getDifference(Column col) {
        return this.value - col.value;
    }

    public Column plusColumn(int number) {
        return find((char) (value + number));
    }

    public static List<Column> columnPath(Column src, Column dst) {
        List<Column> columns = new ArrayList<>();
        char srcCol = src.value;
        while (srcCol != dst.value) {
            srcCol += 1;
            columns.add(find(srcCol));
        }
        return columns;
    }

    public char getValue() {
        return value;
    }
}
