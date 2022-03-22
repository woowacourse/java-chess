package chess;

import java.util.ArrayList;
import java.util.List;

public class Position  {

    private final Column column;
    private final Row row;

    public Position(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    public static List<Position> init() {
        List<Position> positions = new ArrayList<>();

        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                positions.add(new Position(column, row));
            }
        }
        return positions;
    }
}
