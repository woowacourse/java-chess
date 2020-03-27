package chess.domain.board;

import java.util.Arrays;
import java.util.List;

public enum Score {
    QUEEN("q", 9),
    ROOK("r", 5),
    BISHOP("b", 3),
    KNIGHT("n", 2.5),
    PAWN("p", 1);

    private final String name;
    private final double score;

    Score(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public static double calculateScoreOf(List<String> column) {
        double score = Arrays.stream(Score.values())
                .filter(value -> column.contains(value.name))
                .mapToDouble(value -> value.score)
                .sum();
        return score - calculatePenalty(column);
    }

    private static double calculatePenalty(List<String> column) {
        if (countPawn(column) >= 2) {
            return countPawn(column) * 0.5;
        }
        return 0;
    }

    private static long countPawn(List<String> column) {
        return column.stream()
                .filter(value -> value.equals("p"))
                .count();
    }
}
