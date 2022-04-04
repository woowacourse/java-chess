package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.EnumMap;
import java.util.List;

public class Blank extends Piece {
    public static final String SYMBOL = ".";
    private static final float SCORE = 0;

    public Blank(Team team, Position position) {
        super(team, SYMBOL, position, SCORE);
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

    @Override
    protected List<Direction> getDirections() {
        throw new IllegalArgumentException("Blank는 방향을 가질 수 없습니다.");
    }
}