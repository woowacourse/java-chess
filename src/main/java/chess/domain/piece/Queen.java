package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;


public class Queen extends Piece {
    private static final String SYMBOL = "Q";
    private static final float SCORE = 9.0f;

    public Queen(Team team) {
        super(team, SYMBOL, SCORE);
    }

    @Override
    public List<Position> findPath(Position source, Position destination) {
        Direction direction = findDirection(source, destination);
        return getPath(destination, direction, source.plusDirection(direction));
    }

    @Override
    protected List<Direction> getDirections() {
        return Arrays.asList(Direction.NORTH, Direction.EAST, Direction.SOUTH,
                Direction.WEST, Direction.NORTHEAST, Direction.SOUTHEAST, Direction.SOUTHWEST, Direction.NORTHWEST);
    }
}