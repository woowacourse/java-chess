package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

public class Bishop extends Piece {
    private static final String SYMBOL = "B";
    private static final float SCORE = 3.0f;

    public Bishop(Team team) {
        super(team, SYMBOL, SCORE);
    }

    @Override
    public List<Position> findPath(Position source, Position destination) {
        Direction direction = findDirection(source, destination);
        return getPath(destination, direction, source.plusDirection(direction));
    }

    @Override
    protected List<Direction> getDirections() {
        return Arrays.asList(Direction.NORTHEAST, Direction.SOUTHEAST, Direction.SOUTHWEST, Direction.NORTHWEST);
    }
}
