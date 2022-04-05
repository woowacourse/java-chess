package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Column;
import chess.domain.position.Position;

import java.util.EnumMap;
import java.util.List;

public class Blank extends Piece {
    public static final String SYMBOL = ".";
    private static final float SCORE = 0;

    public Blank(Team team) {
        super(team, SYMBOL, SCORE);
    }

    public static EnumMap<Column, Piece> from(Team team) {
        EnumMap<Column, Piece> pawns = new EnumMap<>(Column.class);
        for (Column column : Column.values()) {
            pawns.put(column, new Blank(team));
        }
        return pawns;
    }

    @Override
    public List<Position> findPath(Position source, Position destination) {
        return null;
    }

    @Override
    public boolean isBlank() {
        return true;
    }

    @Override
    protected List<Direction> getDirections() {
        throw new IllegalArgumentException("Blank는 방향을 가질 수 없습니다.");
    }
}