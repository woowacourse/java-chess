package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Team team, Position position) {
        super(team, Bishop.class.getSimpleName(), position);
    }

    private Direction findDirection(Position destination) {
        for (Direction direction : Direction.diagonalDirection()) {
            if (destination.getCol().getDifference(position.getCol()) * direction.getXDegree()
                    == (destination.getRow().getDifference(position.getRow()) * direction.getYDegree())
            && (destination.getRow().getDifference(position.getRow()) * direction.getYDegree()) > 0) {
                return direction;
            }
        }
        throw new IllegalArgumentException("해당 위치로 말이 움직일 수 없습니다.");
    }

    @Override
    public List<Position> findPath(Position destination) {
        Direction direction = findDirection(destination);
        return getPath(destination, direction, position.getCol(), position.getRow());
    }

    private List<Position> getPath(Position destination, Direction direction, Column column, Row row) {
        List<Position> positions = new ArrayList<>();
        while(column != destination.getCol()) {
            column = column.plusColumn(direction.getXDegree());
            row = row.plusRow(direction.getYDegree());
            positions.add(new Position(column, row));
        }
        return positions;
    }
}
