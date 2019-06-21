package chess.domain;

import java.util.Arrays;
import java.util.Objects;

import static chess.domain.Direction.*;

public class Queen extends Piece {
    public Queen(Team team) {
        super(team, Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Queen queen = (Queen) o;
        return team == queen.team &&
                Objects.equals(candidateDirection, queen.candidateDirection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, candidateDirection);
    }
}
