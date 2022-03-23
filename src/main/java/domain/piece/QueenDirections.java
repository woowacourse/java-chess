package domain.piece;

import domain.Direction;
import domain.DirectionsGenerator;
import java.util.ArrayList;
import java.util.List;

public class QueenDirections implements DirectionsGenerator {

    private List<Direction> directions;

    public QueenDirections() {
        this.directions = new ArrayList<>();
    }

    public List<Direction> generate() {
        directions.add(Direction.NORTHEAST);
        directions.add(Direction.NORTHWEST);
        directions.add(Direction.SOUTHEAST);
        directions.add(Direction.SOUTHWEST);
        directions.add(Direction.EAST);
        directions.add(Direction.WEST);
        directions.add(Direction.SOUTH);
        directions.add(Direction.NORTH);
        return directions;
    }
}
