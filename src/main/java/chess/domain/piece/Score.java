package chess.domain.piece;

public class Score {

    private static final double PAWN_PANALTY_SCORE = 0.5d;

    private final double value;

    public Score(final double value) {
        this.value = value;
    }

    public double value() {
        return this.value;
    }

    public Score plus(Score score) {
        return new Score(this.value + score.value);
    }

    public double score(){
        return this.value;
    }

    public Score calculatePawnPenaltyScore(int pawnCountInLine) {
        return new Score(this.value - (PAWN_PANALTY_SCORE * pawnCountInLine));
    }
}
