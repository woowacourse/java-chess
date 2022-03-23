package domain.directions;

import java.util.ArrayList;
import java.util.List;

public class BlackPawnDirections implements DirectionsGenerator {

    private List<Direction> directions;

    public BlackPawnDirections() {
        this.directions = new ArrayList<>();
    }

    public List<Direction> generate() {
        directions.add(Direction.SOUTHWEST);
        directions.add(Direction.SOUTHEAST);
        directions.add(Direction.SOUTH);
        return directions;
    }
}
