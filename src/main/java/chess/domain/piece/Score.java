package chess.domain.piece;

import java.util.Objects;

public final class Score {
    
    private final double score;
    
    private Score(double score) {
        this.score = score;
    }
    
    public static Score from(double score) {
        return new Score(score);
    }
    
    public Score add(Score score) {
        return new Score(this.score + score.score);
    }
    
    public Score multiply(double score) {
        return new Score(this.score * score);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.score);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final Score score1 = (Score) o;
        return Double.compare(score1.score, this.score) == 0;
    }
    
    public double getScore() {
        return this.score;
    }
}
