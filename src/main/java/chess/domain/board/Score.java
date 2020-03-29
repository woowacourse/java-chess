package chess.domain.board;

import java.util.Arrays;
import java.util.List;

public enum Score {
    KING("K", 0),
    QUEEN("Q", 9),
    ROOK("R", 5),
    BISHOP("B", 3),
    KNIGHT("N", 2.5),
    PAWN("P", 1);

    private final String name;
    private final double score;

    Score(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public static double calculateScoreOf(List<String> column) {
        double score = column.stream()
                .mapToDouble(Score::getScoreOf)
                .sum();
        return score - calculatePenalty(column);
    }

    private static double calculatePenalty(List<String> column) {
        if (countPawnIn(column) >= 2) {
            return countPawnIn(column) * 0.5;
        }
        return 0;
    }

    private static long countPawnIn(List<String> column) {
        return column.stream()
                .filter(value -> value.equals("P"))
                .count();
    }

    private static double getScoreOf(String piece) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(piece))
                .findFirst()
                .orElseThrow(NullPointerException::new)
                .score;
    }
}
