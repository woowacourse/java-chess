package chess.domain.piece;

import java.util.Objects;

public class PieceScore {

    private final Double score;

    private PieceScore(Double score) {
        this.score = score;
    }

    public static PieceScore from(String score) {
        double value = Double.parseDouble(score);

        return new PieceScore(value);
    }
    public static PieceScore getZero() {
        return new PieceScore(0d);
    }


    public PieceScore append(PieceScore other) {
        return new PieceScore(this.score + other.score);
    }

    public boolean isGreaterThan(PieceScore other) {
        return this.score > other.score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PieceScore that = (PieceScore) o;
        return Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    public Double getValue() {
        return score;
    }
}
