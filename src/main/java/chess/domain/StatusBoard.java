package chess.domain;

import java.util.Objects;

public class StatusBoard {
    private static final double SCORE_MIN_CONDITION = 0;
    private static final double SCORE_MAX_CONDITION = 76;

    private final double blackScore;
    private final double whiteScore;

    public StatusBoard(double blackScore, double whiteScore) {
        scoreValidation(blackScore);
        scoreValidation(whiteScore);
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    private void scoreValidation(double score) {
        if (score < SCORE_MIN_CONDITION || score > SCORE_MAX_CONDITION) {
            throw new IllegalArgumentException("점수 오류입니다");
        }
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusBoard that = (StatusBoard) o;
        return Double.compare(that.blackScore, blackScore) == 0 &&
                Double.compare(that.whiteScore, whiteScore) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(blackScore, whiteScore);
    }
}
