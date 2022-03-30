package chess.domain.piece;

import chess.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(Team team, Position position) {
        super(team, "P", position, 1);
    }

    public static EnumMap<Column, Piece> from(int row, Team team) {
        EnumMap<Column, Piece> pawns = new EnumMap<>(Column.class);
        for (Column column : Column.values()) {
            pawns.put(column, new Pawn(team, new Position(column, Row.find(row))));
        }
        return pawns;
    }

    @Override
    public List<Position> findPath(Position destination) throws IllegalArgumentException {
        List<Direction> directions = getDirection();
        Direction direction = findDirection(destination, directions);
        return getPath(destination, direction, position.getCol(), position.getRow());
    }

    private List<Direction> getDirection() {
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

    private Direction findDirection(Position destination, List<Direction> directions) {
        int colDifference = destination.getColDifference(position.getCol());
        int rowDifference = destination.getRowDifference(position.getRow());
        return Direction.find(rowDifference, colDifference, directions);
    }

    @Override
    protected List<Position> getPath(Position destination, Direction direction, Column col, Row row) {
        List<Position> positions = new ArrayList<>();
        while (!(col == destination.getCol() && row == destination.getRow())) {
            col = col.plusColumn(direction.getXDegree());
            row = row.plusRow(direction.getYDegree());
            positions.add(new Position(col, row));
        }
        return positions;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
