package chess.domain.piece;

import chess.domain.*;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    List<Direction> directions = List.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);

    public Rook(Team team, Position position) {
        super(team, "R", position, 5);
    }

    private Direction findDirection(Position destination) {
        int colDifference = destination.getColDifference(position.getCol());
        int rowDifference = destination.getRowDifference(position.getRow());
        return Direction.find(rowDifference, colDifference, directions);
    }

    @Override
    public List<Position> findPath(Position destination) {
        Direction direction = findDirection(destination);
        return getPath(destination, direction,
                position.getCol().plusColumn(direction.getXDegree()),
                position.getRow().plusRow(direction.getYDegree()));
    }

    private List<Position> getPath(Position destination, Direction direction, Column col, Row row) {
        List<Position> positions = new ArrayList<>();
        while (!(col == destination.getCol() && row == destination.getRow())) {
            positions.add(new Position(col, row));
            col = col.plusColumn(direction.getXDegree());
            row = row.plusRow(direction.getYDegree());
        }
        return positions;
    }
}