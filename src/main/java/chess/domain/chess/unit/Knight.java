package chess.domain.chess.unit;

import chess.domain.chess.Team;
import chess.domain.geometric.Vector;
import chess.util.DoubleCompare;

public class Knight extends Unit {
    private static final Double KNIGHT_LENGTH = Math.sqrt(5);

    public Knight(Team team) {
        super(team);
    }

    @Override
    public boolean validateDirection(Vector vector) {
        return DoubleCompare.deltaCompare(vector.calculateDistance(), KNIGHT_LENGTH);
    }

    @Override
    public String toString() {
        return "나이트";
    }
}
