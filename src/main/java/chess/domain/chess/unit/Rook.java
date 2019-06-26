package chess.domain.chess.unit;

import chess.domain.UnitInformation;
import chess.domain.chess.Team;
import chess.domain.geometric.Direction;
import chess.domain.geometric.Vector;

import java.util.List;

public class Rook extends Unit {
    private static final double SCORE = UnitInformation.ROOK.score();

    private static List<Direction> directions;

    static {
        directions = Direction.plusShape();
    }

    public Rook(Team team) {
        super(team, UnitInformation.ROOK.name());
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
    public String getName() {
        return "룩";
    }

    @Override
    public String toString() {
        return getTeam().applyUpperCaseOrLowerCaseByTeam(UnitInformation.ROOK.symbol());
    }
}
