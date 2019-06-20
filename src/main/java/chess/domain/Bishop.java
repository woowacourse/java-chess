package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Unit {
    private static List<Direction> directions = new ArrayList<>();

    static {
        directions.add(Direction.NORTHEAST);
        directions.add(Direction.NORTHWEST);
        directions.add(Direction.SOUTHEAST);
        directions.add(Direction.SOUTHWEST);
    }

    public Bishop(Team team) {
        super(team);
    }

    @Override
    public boolean validateDirection(Vector another) {
        return directions.stream()
                .anyMatch(direction -> direction.isParallelTo(another));
    }
}
