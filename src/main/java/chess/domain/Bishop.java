package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Bishop implements Movable {

    private static final List<Direction> directions;

    static {
        directions = List.of(Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);
    }

    @Override
    public List<Path> findMovablePaths(final Position current) {
        List<Path> paths = new ArrayList<>();
        for (Direction direction : directions) {
            paths.add(Path.ofMultiPath(current, direction));
        }
        return paths;
    }

}
