package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Rook implements Movable {

    private static final List<Direction> directions;

    static {
        directions = List.of(Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH);
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
