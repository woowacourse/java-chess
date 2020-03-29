package chess.domain;

import chess.domain.piece.Placeable;

import java.util.Objects;
import java.util.Set;

public class Pieces {
    private Set<Placeable> placeables;

    public Pieces(Set<Placeable> placeables) {
        this.placeables = placeables;
    }

    public double sumScore() {
        double totalScore = 0;

        for (Placeable placeable : placeables) {
            totalScore += placeable.getScore();
        }

        return totalScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pieces pieces1 = (Pieces) o;
        return Objects.equals(placeables, pieces1.placeables);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeables);
    }

    @Override
    public String toString() {
        return "Pieces{" +
                "placeables=" + placeables +
                '}';
    }
}
