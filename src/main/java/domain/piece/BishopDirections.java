package domain.piece;

import domain.Direction;
import domain.DirectionsGenerator;
import java.util.ArrayList;
import java.util.List;

public class BishopDirections implements DirectionsGenerator {
    private List<Direction> directions;

    public BishopDirections() {
        this.directions = new ArrayList<>();
    }

    public List<Direction> generate() {
        directions.add(Direction.NORTHEAST);
        directions.add(Direction.NORTHWEST);
        directions.add(Direction.SOUTHEAST);
        directions.add(Direction.SOUTHWEST);
        return directions;
    }
}
