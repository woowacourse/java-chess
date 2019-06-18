package chess.domain;

import java.util.Objects;

public class Bishop implements Piece {
    private final boolean teamColor;

    public Bishop(boolean teamColor) {
        this.teamColor = teamColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bishop bishop = (Bishop) o;
        return teamColor == bishop.teamColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamColor);
    }
}
