package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceName;
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

    public double calculateColumns(List<List<Piece>> pieces) {
        return pieces.stream()
                .mapToDouble(this::calculateOneColumn)
                .sum();
    }

    public double calculateOneColumn(List<Piece> pieces) {
        long pawnCount = pieces.stream()
                .filter(p -> p.isSamePieceName(PieceName.PAWN))
                .count();
        double sum = pieces.stream()
                .mapToDouble(Piece::getPoint)
                .sum();

        if (pawnCount == 1) {
            return sum;
        }
        return sum - (pawnCount / SCORE_REDUCTION_RATE);
    }
}
