package chess.domain;

import java.util.List;

public class ScoreCalculator {
    private final List<Square> whites;
    private final List<Square> blacks;

    public ScoreCalculator(final List<Square> whites, final List<Square> blacks) {
        this.whites = whites;
        this.blacks = blacks;
    }

    public double getWhiteScore() {
        double sum = 0;
        for (final Square white : whites) {
            sum += white.getScore();
        }
        return sum;
    }

    public double getBlackScore() {
        double sum = 0;
        for (final Square black : blacks) {
            sum += black.getScore();
        }
        return sum;
    }
}

