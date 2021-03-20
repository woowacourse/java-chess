package chess.domain.piece;

public class Score {

    private final double score;

    Score(double score){
        this.score = score;
    }

    public Score sum(Score that){
        return new Score(score + that.score);
    }

}
