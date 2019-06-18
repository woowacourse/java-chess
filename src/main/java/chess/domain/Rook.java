package chess.domain;

import java.util.Objects;

public class Rook implements Piece{
    private final boolean teamColor;

    public Rook(boolean teamColor) {
        this.teamColor = teamColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rook rook = (Rook) o;
        return teamColor == rook.teamColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamColor);
    }
}
