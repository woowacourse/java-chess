package chess.domain.game;

public class Score {
    private final double score;

    private Score(double score) {
        this.score = score;
    }

    public static Score of(double score){
        return new Score(score);
    }

    public Score add(double addScore){
        return of(score + addScore);
    }

    public Score subtract(double subtractScore) {
        return of(score - subtractScore);
    }

    public double getScore() {
        return score;
    }
}
