package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import chess.domain.Team;

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
}