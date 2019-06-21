package chess.domain;

import java.util.Arrays;
import java.util.Objects;

import static chess.domain.Direction.*;

public class Rook extends Piece {
    private static final double SCORE = 5.0;

    public Rook(Team team) {
        super(team, Arrays.asList(NORTH, EAST, SOUTH, WEST));
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rook rook = (Rook) o;
        return team == rook.team &&
                Objects.equals(candidateDirection, rook.candidateDirection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, candidateDirection);
    }
}
