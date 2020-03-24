package chess.domain.position;

import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.*;

public class Positions {
    private static final Map<String, Position> positions = new LinkedHashMap<>();

    static {
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                positions.put(key(row, column), new Position(row, column));
            }
        }
    }

    private Positions() {

    }

    public static Position of(Row row, Column column) {
        return positions.get(key(row, column));
    }

    private static String key(Row row, Column column) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(row.getValue());
        stringBuffer.append(column.getValue());
        return stringBuffer.toString();
    }

    public static List<Position> getValues() {
        return Collections.unmodifiableList(new ArrayList<>(positions.values()));
    }
}
