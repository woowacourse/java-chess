package chess.domain.board;

import chess.domain.piece.Piece;

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

    public static double calculateScoreOf(List<Piece> column) {
        double score = column.stream()
                .mapToDouble(Score::getScoreOf)
                .sum();
        return score - calculatePenalty(column);
    }

    private static double getScoreOf(Piece piece) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(piece.getName()))
                .findFirst()
                .orElseThrow(NullPointerException::new)
                .score;
    }

    private static double calculatePenalty(List<Piece> column) {
        if (countPawnIn(column) >= 2) {
            return countPawnIn(column) * 0.5;
        }
        return 0;
    }

    private static long countPawnIn(List<Piece> column) {
        return column.stream()
                .filter(value -> value.getName().equals("P"))
                .count();
    }
}
