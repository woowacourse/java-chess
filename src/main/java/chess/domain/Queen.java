package chess.domain;

import java.util.List;
import java.util.Objects;

public class Queen implements Piece{
    private final boolean teamColor;

    public Queen(boolean teamColor) {
        this.teamColor = teamColor;
    }

    @Override
    public List<Point> getCandidatePoints(Point start, Point end) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Queen queen = (Queen) o;
        return teamColor == queen.teamColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamColor);
    }
}
