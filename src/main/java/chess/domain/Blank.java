package chess.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Blank extends Piece {

    public Blank(Team team, Position position) {
        super(team, Blank.class.getSimpleName(), position);
    }

    public static EnumMap<Column, Piece> from(int row, Team team) {
        EnumMap<Column, Piece> pawns = new EnumMap<>(Column.class);
        for (Column column : Column.values()) {
            pawns.put(column, new Blank(team, new Position(column, Row.find(row))));
        }
        return pawns;
    }
}