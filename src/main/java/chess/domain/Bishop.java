package chess.domain;

import java.util.Arrays;
import java.util.Objects;

import static chess.domain.Direction.*;

public class Bishop extends Piece {
    private static final double SCORE = 3.0;

    public Bishop(Team team) {
        super(team, Arrays.asList(NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST));
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bishop bishop = (Bishop) o;
        return team == bishop.team &&
                Objects.equals(candidateDirection, bishop.candidateDirection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, candidateDirection);
    }
}
