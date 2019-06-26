package chess.domain.chess.unit;

import chess.domain.UnitInformation;
import chess.domain.chess.Team;
import chess.domain.geometric.Direction;
import chess.domain.geometric.Vector;

import java.util.List;

public class Bishop extends Unit {
    private static final double SCORE = UnitInformation.BISHOP.score();
    private static List<Direction> directions;

    static {
        directions = Direction.multiplierShape();
    }

    public Bishop(Team team) {
        super(team, UnitInformation.BISHOP.name());
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
        return getTeam().applyUpperCaseOrLowerCaseByTeam(UnitInformation.BISHOP.symbol());
    }
}
