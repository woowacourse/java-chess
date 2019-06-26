package chess.domain.chess.unit;

import chess.domain.chess.Team;
import chess.domain.geometric.Direction;
import chess.domain.geometric.Vector;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Queen extends Unit {
    private static final double SCORE = 9;
    private static final String NAME = "Queen";
    private static final String SYMBOL = "Q";

    private static List<Direction> directions;

    static {
        directions = Arrays.stream(Direction.values())
                .collect(toList());
    }

    public Queen(Team team) {
        super(team, NAME);
    }

    @Override
    public boolean validateDirection(Vector another) {
        return directions.stream()
                .anyMatch(direction -> direction.isParallelTo(another));
    }

    @Override
    public double score() {
        return SCORE;
    }

    @Override
    public String toString() {
        return getTeam().getUnitName(SYMBOL);
    }
}
