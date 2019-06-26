package chess.domain.chess.unit;

import chess.domain.chess.Team;
import chess.domain.geometric.Direction;
import chess.domain.geometric.Vector;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Unit {
    private static final double SCORE = 5;
    private static final String NAME = "Rook";
    private static final String SYMBOL = "R";

    private static List<Direction> directions;

    static {
        directions = Direction.plusShape();
    }

    public Rook(Team team) {
        super(team, NAME);
    }

    @Override
    public boolean validateDirection(Vector another) {
        return directions.stream()
                .anyMatch(vector -> vector.isParallelTo(another));
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
