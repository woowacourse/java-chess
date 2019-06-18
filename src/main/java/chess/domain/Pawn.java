package chess.domain;

import java.util.Objects;

public class Pawn implements Piece{
    private final boolean teamColor;

    public Pawn(boolean teamColor) {
        this.teamColor = teamColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pawn pawn = (Pawn) o;
        return teamColor == pawn.teamColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamColor);
    }
}
