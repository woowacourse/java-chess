package chess.domain.piece;

import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.Team;

import java.util.EnumMap;
import java.util.List;

public class Blank extends Piece {

    public Blank(Team team, Position position) {
        super(team, ".", position, 0);
    }

    public static EnumMap<Column, Piece> from(int row, Team team) {
        EnumMap<Column, Piece> pawns = new EnumMap<>(Column.class);
        for (Column column : Column.values()) {
            pawns.put(column, new Blank(team, Position.of(column, Row.find(row))));
        }
        return pawns;
    }

    @Override
    public List<Position> findPath(Position destination) {
        return null;
    }

    @Override
    public boolean isBlank() {
        return true;
    }

    @Override
    public void move(Piece destination) {
        throw new IllegalArgumentException("Blank는 움직일 수 없습니다.");
    }
}