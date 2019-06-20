package chess.domain;

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
}
