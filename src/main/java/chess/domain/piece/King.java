package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

public class King extends Piece {
    private static final String SYMBOL = "K";
    private static final float SCORE = 0;

    public King(Team team) {
        super(team, SYMBOL, SCORE);
    }

    @Override
    public List<Position> findPath(Position source, Position destination) {
        findDirection(source, destination);
        validateDirection(source, destination);
        return List.of();
    }

    private void validateDirection(Position source, Position destination) {
        int colDifference = destination.getColDifference(source.getCol());
        int rowDifference = destination.getRowDifference(source.getRow());
        Direction.findDirection(rowDifference, colDifference, getDirections());
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    protected List<Direction> getDirections() {
        return Arrays.asList(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST,
                Direction.NORTHEAST, Direction.SOUTHEAST, Direction.SOUTHWEST, Direction.NORTHWEST);
    }
}