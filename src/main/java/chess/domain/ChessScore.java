package chess.domain;

import java.util.Objects;

public class ChessScore {

    private final double whiteScore;
    private final double blackScore;

    public ChessScore(double whiteScore, double blackScore) {
        validateScore(whiteScore);
        validateScore(blackScore);
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    private void validateScore(double score) {
        if (score < 0.0) {
            throw new IllegalArgumentException();
        }
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    @Override
    public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
        ChessScore that = (ChessScore) o;
        return Double.compare(that.whiteScore, whiteScore) == 0
                && Double.compare(that.blackScore, blackScore) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(whiteScore, blackScore);
    }
}
