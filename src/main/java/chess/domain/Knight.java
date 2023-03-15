package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Knight implements Movable {

    private static final List<Direction> directions;

    static {
        directions = List.of(Direction.EAST_DOWN, Direction.EAST_UP, Direction.SOUTH_LEFT, Direction.SOUTH_RIGHT,
                Direction.WEST_DOWN, Direction.WEST_UP, Direction.NORTH_LEFT, Direction.NORTH_RIGHT);
    }

    @Override
    public List<Path> findMovablePaths(final Position current) {
        List<Path> paths = new ArrayList<>();
        for (Direction direction : directions) {
            paths.add(Path.ofSinglePath(current, direction));
        }
        return paths;
    }

}
