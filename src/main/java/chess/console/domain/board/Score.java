package chess.console.domain.board;

public class Score {

    private final double score;

    public Score(double score) {
        validateNegative(score);

        this.score = score;
    }

    private void validateNegative(double score) {
        if (score < 0) {
            throw new IllegalArgumentException("계산된 점수가 잘못되었습니다.");
        }
    }

    public double getScore() {
        return score;
    }
}
