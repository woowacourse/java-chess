package chess.board;

import java.util.ArrayList;
import java.util.List;

public class Generator {

    public static List<Position> board() {
        List<Position> positions = new ArrayList<>();

        for (Column column : Column.values()) {
            for (Row row : Row.values()) {
                positions.add(new Position(column, row));
            }
        }

        return positions;
    }


}
