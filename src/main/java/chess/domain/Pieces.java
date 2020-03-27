package chess.domain;

import chess.domain.piece.Piece;

import java.util.Objects;
import java.util.Set;

public class Pieces {
    private Set<Piece> pieces;

    public Pieces(Set<Piece> pieces) {
        this.pieces = pieces;
    }

    public double sumScore() {
        double totalScore = 0;

        for (Piece piece : pieces) {
            totalScore += piece.getScore();
        }

        return totalScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pieces pieces1 = (Pieces) o;
        return Objects.equals(pieces, pieces1.pieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieces);
    }

    @Override
    public String toString() {
        return "Pieces{" +
                "pieces=" + pieces +
                '}';
    }
}
