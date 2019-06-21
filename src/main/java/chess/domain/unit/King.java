package chess.domain.unit;

import chess.domain.geometric.Direction;
import chess.domain.Team;
import chess.domain.geometric.Vector;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class King extends Unit {
    private static List<Direction> directions;

    static {
        directions = Arrays.stream(Direction.values())
                .collect(toList());
    }

    public King(Team team) {
        super(team);
    }

    @Override
    public boolean validateDirection(Vector another) {
        return directions.stream()
                .anyMatch(direction -> direction.isEqualTo(another));
    }
}
