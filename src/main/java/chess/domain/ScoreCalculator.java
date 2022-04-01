package chess.domain;

import java.util.List;

public class ScoreCalculator {

    private static final double SCORE_REDUCTION_RATE = 2.0;

    private static ScoreCalculator instance;

    private ScoreCalculator() {
    }

    public static ScoreCalculator getInstance() {
        if (instance == null) {
            instance = new ScoreCalculator();
        }
        return instance;
    }

    public double calculateColumns(final List<Pieces> piecesOfAllColumns) {
        return piecesOfAllColumns.stream()
                .mapToDouble(this::calculateOneColumn)
                .sum();
    }

    public double calculateOneColumn(final Pieces pieces) {
        final long pawnCount = pieces.getPawnCount();
        final double sum = pieces.getSumOfScore();

        if (pawnCount == 1) {
            return sum;
        }
        return sum - (pawnCount / SCORE_REDUCTION_RATE);
    }
}
