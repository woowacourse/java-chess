package domain.directions;

import java.util.ArrayList;
import java.util.List;

public class KnightDirections implements DirectionsGenerator {

    private final List<Direction> directions;

    public KnightDirections() {
        this.directions = new ArrayList<>();
    }

    public List<Direction> generate() {
        directions.add(Direction.NORTHEAST_NORTH);
        directions.add(Direction.NORTHWEST_NORTH);
        directions.add(Direction.NORTHWEST_WEST);
        directions.add(Direction.SOUTHWEST_WEST);
        directions.add(Direction.NORTHEAST_EAST);
        directions.add(Direction.SOUTHEAST_EAST);
        directions.add(Direction.SOUTHEAST_SOUTH);
        directions.add(Direction.SOUTHWEST_SOUTH);
        return directions;
    }
}
