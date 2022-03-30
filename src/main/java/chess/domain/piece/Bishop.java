package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Team;

import java.util.List;

public class Bishop extends Piece {
    private final List<Direction> directions = List.of(Direction.NORTHEAST, Direction.SOUTHEAST, Direction.SOUTHWEST, Direction.NORTHWEST);

    public Bishop(Team team, Position position) {
        super(team, "B", position, 3);
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
}
