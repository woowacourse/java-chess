package chess.domain;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Team team, Position position) {
        super(team, Pawn.class.getSimpleName(), position);
    }

    public static EnumMap<Column, Piece> from(int row, Team team) {
        EnumMap<Column, Piece> pawns = new EnumMap<>(Column.class);
        for (Column column : Column.values()) {
            pawns.put(column, new Pawn(team, new Position(column, Row.find(row))));
        }
        return pawns;
    }
}
