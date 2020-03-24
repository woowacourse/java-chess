package chess.position;

import chess.position.component.Column;
import chess.position.component.Row;

import java.util.LinkedHashMap;
import java.util.Map;

public class Positions {
    private static final Map<String, Position> positions = new LinkedHashMap<>();

    static {
        for (Column column : Column.values()) {
            for (Row row : Row.values()) {
                positions.put(key(column, row), new Position(column, row));
            }
        }
    }

    public static Position of(Column column, Row row) {
        return positions.get(key(column, row));
    }

    private static String key(Column column, Row row) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(column.getValue());
        stringBuffer.append(row.getValue());
        return stringBuffer.toString();
    }
}
