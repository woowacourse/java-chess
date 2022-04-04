package chess.domain.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Column {
    A(1, 'a'),
    B(2, 'b'),
    C(3, 'c'),
    D(4, 'd'),
    E(5, 'e'),
    F(6, 'f'),
    G(7, 'g'),
    H(8, 'h'),
    ;

    private static final int COLUMN_FLIP_CRITERIA = 9;
    private static final String INVALID_COLUMN_MESSAGE = "존재하지 않는 열입니다.";

    private final int number;
    private final char name;

    Column(final int number, char name) {
        this.number = number;
        this.name = name;
    }

    private static Column from(int number) {
        return from((char) (number + 96));
    }

    public static Column from(char name) {
        return Arrays.stream(values())
            .filter(column -> column.name == name)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(INVALID_COLUMN_MESSAGE));
    }

    public Column flip() {
        return Arrays.stream(Column.values())
            .filter(it -> it.number == (COLUMN_FLIP_CRITERIA - number))
            .findFirst()
            .orElseThrow();
    }

    public int directedDistance(Column otherColumn) {
        return otherColumn.number - number;
    }

    public int distance(Column otherColumn) {
        return Math.abs(directedDistance(otherColumn));
    }

    public List<Column> pathTo(Column otherColumn) {
        if (number < otherColumn.number) {
            return upPathTo(otherColumn);
        }
        return downPathTo(otherColumn);
    }

    private List<Column> upPathTo(Column otherColumn) {
        List<Column> path = new ArrayList<>();
        for (int i = number + 1; i < otherColumn.number; i++) {
            path.add(Column.from(i));
        }
        return path;
    }

    private List<Column> downPathTo(Column otherColumn) {
        List<Column> path = new ArrayList<>();
        for (int i = number - 1; i > otherColumn.number; i--) {
            path.add(Column.from(i));
        }
        return path;
    }

    public Column nextWith(final UnitDirectVector direction) {
        return Column.from(direction.nextColumnNumber(number));
    }

    public boolean isNextValid(final UnitDirectVector direction) {
        final int nextColumnNumber = direction.nextColumnNumber(number);
        return A.number <= nextColumnNumber && nextColumnNumber <= H.number;
    }
}
