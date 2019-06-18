package chess.domain;

import java.util.Objects;

public class Knight implements Piece{
    private final boolean teamColor;

    public Knight(boolean teamColor) {
        this.teamColor = teamColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Knight knight = (Knight) o;
        return teamColor == knight.teamColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamColor);
    }
}
