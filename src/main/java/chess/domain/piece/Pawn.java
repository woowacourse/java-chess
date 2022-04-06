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

    public Pawn(Team team) {
        super(team, SYMBOL, SCORE);
    }

    public static EnumMap<Column, Piece> from(Team team) {
        EnumMap<Column, Piece> pawns = new EnumMap<>(Column.class);
        for (Column column : Column.values()) {
            pawns.put(column, new Pawn(team));
        }
        return pawns;
    }

    @Override
    public List<Position> findPath(Position source, Position destination) {
        Direction direction = findDirection(source, destination);
        return getPath(destination, direction, source);
    }

    @Override
    protected Direction findDirection(Position source, Position destination) {
        int colDifference = destination.getColDifference(source.getCol());
        int rowDifference = destination.getRowDifference(source.getRow());
        validateDirection(source, colDifference, rowDifference);
        return Direction.find(rowDifference, colDifference, getDirections(source));
    }

    private void validateDirection(Position source, int colDifference, int rowDifference) {
        Direction.findDirection(rowDifference, colDifference, getDirections(source));
    }

    @Override
    protected List<Direction> getDirections() {
        return null;
    }

    protected List<Direction> getDirections(Position source) {
        if (isFirstTurn(source) && isBlackTeam()) {
            return Arrays.asList(Direction.SOUTH, Direction.SOUTHEAST, Direction.SOUTHWEST, Direction.SOUTH_TWO_STEP);
        }
        if (isBlackTeam()) {
            return Arrays.asList(Direction.SOUTH, Direction.SOUTHEAST, Direction.SOUTHWEST);
        }
        if (isFirstTurn(source) && !isBlackTeam()) {
            return Arrays.asList(Direction.NORTH, Direction.NORTHEAST, Direction.NORTHWEST, Direction.NORTH_TWO_STEP);
        }
        return Arrays.asList(Direction.NORTH, Direction.NORTHEAST, Direction.NORTHWEST);
    }

    private boolean isFirstTurn(Position source) {
        if (!isBlackTeam() && source.getRow() == Row.TWO) {
            return true;
        }
        return isBlackTeam() && source.getRow() == Row.SEVEN;
    }

    @Override
    protected List<Position> getPath(Position destination, Direction direction, Position source) {
        List<Position> positions = new ArrayList<>();
        while (!source.matchPosition(destination)) {
            source = source.plusDirection(direction);
            positions.add(source);
        }
        return positions;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
