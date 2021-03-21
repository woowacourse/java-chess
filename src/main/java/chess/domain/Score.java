package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.kind.Piece;

import java.util.Map;

import static chess.domain.Board.BOARD_SIZE;

public class Score {
    private static final double OVERLAPPED_PAWN_SCORE = 0.5;
    private static final int PAWN_COUNT_THRESHOLD_TO_HALF_SCORE = 2;

    private final double score;

    public Score(Map<Point, Piece> board, Color color) {
        this.score = calculateScore(board, color);
    }

    private double calculateScore(Map<Point, Piece> board, Color color) {
        double totalScore = board.values().stream()
                .filter(piece -> piece.isSameTeam(color))
                .map(Piece::score)
                .reduce((double) 0, Double::sum);

        return totalScore - calculatePawnScore(board, color);
    }

    private double calculatePawnScore(Map<Point, Piece> board, Color color) {
        int pawnCountInColumn = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            int column = i;
            long columnPawnCount = board.keySet().stream()
                    .filter(point -> point.isSameColumn(column))
                    .map(board::get)
                    .filter(piece -> piece.isSameTeam(color))
                    .filter(Piece::isPawn)
                    .count();
            pawnCountInColumn += addColumnPawnCount(columnPawnCount);
        }
        return pawnCountInColumn * OVERLAPPED_PAWN_SCORE;
    }

    private int addColumnPawnCount(long columnPawnCount) {
        if (columnPawnCount >= PAWN_COUNT_THRESHOLD_TO_HALF_SCORE) {
            return (int) columnPawnCount;
        }
        return 0;
    }

    public double getScore() {
        return this.score;
    }
}
