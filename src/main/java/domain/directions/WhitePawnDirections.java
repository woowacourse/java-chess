package domain.directions;

import java.util.ArrayList;
import java.util.List;

public class WhitePawnDirections implements DirectionsGenerator {

    private List<Direction> directions;

    public WhitePawnDirections() {
        this.directions = new ArrayList<>();
    }

    public List<Direction> generate() {
        directions.add(Direction.NORTHEAST);
        directions.add(Direction.NORTHWEST);
        directions.add(Direction.NORTH);
        directions.add(Direction.NORTH_NORTH);
        return directions;
    }
}
