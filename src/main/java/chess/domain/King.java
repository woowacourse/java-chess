package chess.domain;

import java.util.Objects;

public class King implements Piece{
    private final boolean teamColor;

    public King(boolean teamColor) {
        this.teamColor = teamColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        King king = (King) o;
        return teamColor == king.teamColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamColor);
    }
}
