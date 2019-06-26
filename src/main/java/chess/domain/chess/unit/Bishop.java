package chess.domain.chess.unit;

import chess.domain.chess.Team;
import chess.domain.geometric.Direction;
import chess.domain.geometric.Vector;

import java.util.List;

public class Bishop extends Unit {
    private static final double SCORE = 3;
    private static final String NAME = "Bishop";
    private static final String SYMBOL = "B";

    private static List<Direction> directions;

    static {
        directions = Direction.multiplierShape();
    }

    public Bishop(Team team) {
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
