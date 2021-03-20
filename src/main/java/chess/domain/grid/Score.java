package chess.domain.grid;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

public class Score {
    private static final int LINE_COUNT = 8;
    private static final char MIN_X_POSITION = 'a';
    private static final int SAME_COLUMN_BOUND = 2;
    private static final int DIVIDER_FOR_PAWN_SCORE = 2;

    private final Lines lines;

    public Score(final Lines lines) {
        this.lines = lines;
    }

    public double score(final boolean isBlack) {
        return totalScore(isBlack) - pawnScoreInSameColumn(isBlack);
    }

    private double pawnScoreInSameColumn(final boolean isBlack) {
        double pawnScoreToDeduct = 0;
        for (int i = 0; i < LINE_COUNT; i++) {
            pawnScoreToDeduct += pawnCountInSameColumn(isBlack, i);
        }
        return pawnScoreToDeduct / DIVIDER_FOR_PAWN_SCORE;
    }
    public double pawnCountInSameColumn(final boolean isBlack, final int i) {
        double result = 0;
        char x = (char) (MIN_X_POSITION + i);
        int pawnCountInSameColumn =
                (int) lines
                        .lines()
                        .stream()
                        .map(line -> line.piece(x))
                        .filter(piece -> (piece instanceof Pawn && piece.isBlack() == isBlack))
                        .count();
        if (pawnCountInSameColumn >= SAME_COLUMN_BOUND) {
            result += pawnCountInSameColumn;
        }
        return result;
    }

    public double totalScore(final boolean isBlack) {
        return lines
                .lines()
                .stream()
                .flatMap(line -> line.pieces()
                        .stream()
                        .filter(piece -> !piece.isEmpty())
                        .filter(piece -> piece.isBlack() == isBlack)
                        .map(Piece::score))
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
