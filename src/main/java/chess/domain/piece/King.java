package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;

import java.util.List;

public class King extends Piece {
    private List<Direction> directions = List.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.NORTHEAST, Direction.SOUTHEAST, Direction.SOUTHWEST, Direction.NORTHWEST);

    public King(Team team, Position position) {
        super(team, "K", position, 0);
    }

    private Direction findDirection(Position destination) {
        int colDifference = destination.getColDifference(position.getCol());
        int rowDifference = destination.getRowDifference(position.getRow());
        return Direction.find(rowDifference, colDifference, directions);
    }

    @Override
    public List<Position> findPath(Position destination) {
        findDirection(destination);
        return List.of();
    }

    @Override
    public boolean isKing() {
        return true;
    }
}