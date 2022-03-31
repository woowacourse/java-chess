package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

public class King extends Piece {
    private static final String SYMBOL = "K";
    private static final double SCORE = 0;

    public King(Team team, Position position) {
        super(team, SYMBOL, position, SCORE);
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

    @Override
    protected List<Direction> getDirections() {
        return Arrays.asList(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST,
                Direction.NORTHEAST, Direction.SOUTHEAST, Direction.SOUTHWEST, Direction.NORTHWEST);
    }
}