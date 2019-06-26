package chess.domain.chess.unit;

import chess.domain.chess.Team;
import chess.domain.geometric.Vector;
import chess.util.DoubleCompare;

public class Knight extends Unit {
    private static final Double SCORE = 2.5;
    private static final Double KNIGHT_LENGTH = Math.sqrt(5);
    private static final String NAME = "Knight";
    private static final String SYMBOL = "N";

    public Knight(Team team) {
        super(team, NAME);
    }

    @Override
    public boolean validateDirection(Vector vector) {
        return DoubleCompare.deltaCompare(vector.calculateDistance(), KNIGHT_LENGTH);
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
