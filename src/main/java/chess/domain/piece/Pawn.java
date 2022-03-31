package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class Pawn extends Piece {
    private static final String SYMBOL = "P";
    private static final float SCORE = 1.0f;

    public Pawn(Team team, Position position) {
        super(team, SYMBOL, position, SCORE);
    }

    public static EnumMap<Column, Piece> from(int row, Team team) {
        EnumMap<Column, Piece> pawns = new EnumMap<>(Column.class);
        for (Column column : Column.values()) {
            pawns.put(column, new Pawn(team, Position.of(column, Row.find(row))));
        }
        return pawns;
    }

    @Override
    public List<Position> findPath(Position destination) throws IllegalArgumentException {
        Direction direction = findDirection(destination);
        return getPath(destination, direction, position.getCol(), position.getRow());
    }

    @Override
    protected List<Direction> getDirections() {
        if (isFirstTurn() && isBlackTeam()) {
            return Arrays.asList(Direction.SOUTH, Direction.SOUTHEAST, Direction.SOUTHWEST, Direction.SOUTH_TWO_STEP);
        }
        if (isBlackTeam()) {
            return Arrays.asList(Direction.SOUTH, Direction.SOUTHEAST, Direction.SOUTHWEST);
        }
        if (isFirstTurn()) {
            return Arrays.asList(Direction.NORTH, Direction.NORTHEAST, Direction.NORTHWEST, Direction.NORTH_TWO_STEP);
        }
        return Arrays.asList(Direction.NORTH, Direction.NORTHEAST, Direction.NORTHWEST);
    }

    private boolean isFirstTurn() {
        if (!isBlackTeam() && position.getRow() == Row.TWO) {
            return true;
        }
        return isBlackTeam() && position.getRow() == Row.SEVEN;
    }

    @Override
    protected List<Position> getPath(Position destination, Direction direction, Column col, Row row) {
        List<Position> positions = new ArrayList<>();
        while (!(col == destination.getCol() && row == destination.getRow())) {
            col = col.plusColumn(direction.getXDegree());
            row = row.plusRow(direction.getYDegree());
            positions.add(Position.of(col, row));
        }
        return positions;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
