package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Unit {
    private static List<Direction> directions = new ArrayList<>();

    static {
        directions.add(Direction.NORTH);
        directions.add(Direction.SOUTH);
        directions.add(Direction.EAST);
        directions.add(Direction.WEST);
    }

    public Rook(Team team) {
        super(team);
    }

    @Override
    public boolean validateDirection(Vector another) {
        return directions.stream()
                .anyMatch(vector -> vector.isParallelTo(another));
    }
}
