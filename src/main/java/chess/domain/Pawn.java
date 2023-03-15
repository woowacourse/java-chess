package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements Movable {

    private static final List<Integer> FIRST_RANKS = List.of(2, 7);
    private static final List<Direction> directions;

    static {
        // SOUTH or NORTH
        // SOUTH_EAST, SOUTH_WEST or NORTH_EAST, NOUTH_WEST
        directions = List.of(Direction.NORTH, Direction.NORTH_EAST, Direction.NORTH_WEST);
    }

    @Override
    public List<Path> findMovablePaths(final Position current) {
        List<Path> paths = new ArrayList<>();
        for (Direction direction : directions) {
            paths.add(Path.ofSinglePath(current, direction));
        }
        if (FIRST_RANKS.contains(current.getX())) {
            paths.add(Path.ofPawnStartPath(current, Direction.NORTH));
        }
        return paths;
    }
}
