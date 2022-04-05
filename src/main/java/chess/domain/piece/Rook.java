package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

public class Rook extends Piece {
    private static final String SYMBOL = "R";
    private static final float SCORE = 5.0f;

    public Rook(Team team) {
        super(team, SYMBOL, SCORE);
    }

    @Override
    public List<Position> findPath(Position source, Position destination) {
        Direction direction = findDirection(source, destination);
        return getPath(destination, direction,
                source.plusDirection(direction));
    }

    @Override
    protected List<Direction> getDirections() {
        return Arrays.asList(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);
    }
}