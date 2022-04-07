package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.List;

public class ScoreCalculator {

    private static final ScoreCalculator scoreCalculator = new ScoreCalculator();
    private static final int SINGLE_PAWN_COUNT = 1;

    private ScoreCalculator() {
    }

    public static ScoreCalculator getInstance() {
        return scoreCalculator;
    }

    public double calculateColumns(List<List<Piece>> pieces) {
        return pieces.stream()
                .mapToDouble(this::calculateOneColumn)
                .sum();
    }

    public double calculateOneColumn(List<Piece> pieces) {
        long pawnCount = pieces.stream()
                .filter(p -> p.isSamePieceType(PieceType.PAWN))
                .count();
        double sum = pieces.stream()
                .mapToDouble(Piece::getPoint)
                .sum();

        if (pawnCount == SINGLE_PAWN_COUNT) {
            return sum;
        }
        return sum - (pawnCount / 2.0);
    }
}
