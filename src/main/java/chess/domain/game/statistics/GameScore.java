package chess.domain.game.statistics;

import java.util.Objects;

public class GameScore {

    private final double whiteScore;
    private final double blackScore;

    public GameScore(double whiteScore, double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
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
        GameScore gameScore = (GameScore) o;
        return Double.compare(gameScore.whiteScore, whiteScore) == 0
                && Double.compare(gameScore.blackScore, blackScore) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(whiteScore, blackScore);
    }

    @Override
    public String toString() {
        return "GameScore{" + "whiteScore=" + whiteScore + ", blackScore=" + blackScore + '}';
    }
}
