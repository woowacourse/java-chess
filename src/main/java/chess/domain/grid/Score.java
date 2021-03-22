package chess.domain.grid;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

public final class Score {
    private static final char MIN_X_POSITION = Column.FIRST.getName();
    private static final int SAME_COLUMN_BOUND = 2;
    private static final int DIVIDER_FOR_PAWN_SCORE = 2;

    private final Lines lines;

    public Score(final Lines lines) {
        this.lines = lines;
    }

    public double score(final Color color) {
        return totalScore(color) - pawnScoreInSameColumn(color);
    }

    private double pawnScoreInSameColumn(final Color color) {
        double pawnScoreToDeduct = 0;
        for (int i = 0; i < Row.values().length; i++) {
            pawnScoreToDeduct += pawnCountInSameColumn(color, i);
        }
        return pawnScoreToDeduct / DIVIDER_FOR_PAWN_SCORE;
    }

    private double pawnCountInSameColumn(final Color color, final int i) {
        double result = 0;
        char x = (char) (MIN_X_POSITION + i);
        int pawnCountInSameColumn =
                (int) lines
                        .lines()
                        .stream()
                        .map(line -> line.piece(x))
                        .filter(piece -> (piece instanceof Pawn && piece.color() == color))
                        .count();
        if (pawnCountInSameColumn >= SAME_COLUMN_BOUND) {
            result += pawnCountInSameColumn;
        }
        return result;
    }

    private double totalScore(final Color color) {
        return lines
                .lines()
                .stream()
                .flatMap(line -> line.pieces()
                        .stream()
                        .filter(piece -> !piece.isEmpty())
                        .filter(piece -> piece.color() == color)
                        .map(Piece::score))
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
