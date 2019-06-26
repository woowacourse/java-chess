package chess.domain.chess.unit;

import chess.domain.UnitInformation;
import chess.domain.chess.Team;
import chess.domain.geometric.Direction;
import chess.domain.geometric.Vector;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class King extends Unit {
    private static final double SCORE = UnitInformation.KING.score();
    private static List<Direction> directions;

    static {
        directions = Arrays.stream(Direction.values())
                .collect(toList());
    }

    public King(Team team) {
        super(team, UnitInformation.KING.name());
    }

    @Override
    public boolean validateDirection(Vector another) {
        return directions.stream()
                .anyMatch(direction -> direction.isEqualTo(another));
    }

    @Override
    public double score() {
        return SCORE;
    }

    @Override
    public String toString() {
        return getTeam().applyUpperCaseOrLowerCaseByTeam(UnitInformation.KING.symbol());
    }

}
