package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Team;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.EnumMap;
import java.util.List;

public class Pawn extends Piece {

    private static final double PAWN_SCORE = 1;

    public Pawn(Team team, Position position) {
        super(team, position);
    }

    public static EnumMap<Column, Piece> of(int row, Team team) {
        EnumMap<Column, Piece> pawns = new EnumMap<>(Column.class);
        for (Column column : Column.values()) {
            pawns.put(column, new Pawn(team, Position.from(column.getValue() + String.valueOf(row))));
        }
        return pawns;
    }

    @Override
    public double getScore() {
        return PAWN_SCORE;
    }

    @Override
    public List<Position> findPath(Position destination) throws IllegalArgumentException {
        validateSteps(destination);
        if (isBlackTeam()) {
            Direction direction = findDirection(destination, Direction.blackPawnDirection(isFirstTurn()));
            return getPath(destination, direction);
        }
        Direction direction = findDirection(destination, Direction.whitePawnDirection(isFirstTurn()));
        return getPath(destination, direction);
    }

    private void validateSteps(Position destination) {
        int colDiff = destination.getColDifference(position);
        int rowDiff = destination.getRowDifference(position);

        if ((!isFirstTurn() && rowDiff > 1) || rowDiff > 2 || colDiff > 1) {
            throw new IllegalArgumentException("해당 위치로 말이 움직일 수 없습니다.");
        }
    }

    private Direction findDirection(Position destination, List<Direction> directions)
            throws IllegalArgumentException {
        int colDiff = destination.getColDifference(position);
        int rowDiff = destination.getRowDifference(position);
        return Direction.findMatchDirection(colDiff, rowDiff, directions);
    }

    private List<Position> getPath(Position destination, Direction direction) {
        List<Position> positions = position.getPathToDst(destination, direction);
        positions.add(destination);
        return positions;
    }

    private boolean isFirstTurn() {
        if (!isBlackTeam() && position.getRow() == Row.TWO) {
            return true;
        }
        return isBlackTeam() && position.getRow() == Row.SEVEN;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
