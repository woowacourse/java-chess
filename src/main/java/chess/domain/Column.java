package chess.domain;

import java.util.Arrays;

public enum Column {
    A('a', "a"),
    B('b', "b"),
    C('c', "c"),
    D('d', "d"),
    E('e', "e"),
    F('f', "f"),
    G('g', "g"),
    H('h', "h");

    private final char sequence;
    private final String name;

    Column(char sequence, String name) {
        this.sequence = sequence;
        this.name = name;
    }

    public static Column findColumn(String name) {
        return Arrays.stream(Column.values())
                .filter(column -> column.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 컬럼을 찾을 수 없습니다."));
    }

    public static boolean isInChessBoardRange(char columnSequence) {
        return Arrays.stream(Column.values())
                .map(Column::getSequence)
                .anyMatch(column -> column == columnSequence);
    }

    public String getName() {
        return name;
    }

    public char getSequence() {
        return sequence;
    }
}
