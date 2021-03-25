package chess.domain;

import static chess.domain.board.Board.*;

import java.util.Map;

import chess.domain.board.Point;
import chess.domain.board.PositionValue;
import chess.domain.piece.Color;
import chess.domain.piece.kind.Piece;

public final class Score {
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
            .reduce((double)0, Double::sum);

        return totalScore - calculatePawnScore(board, color);
    }

    private double calculatePawnScore(Map<Point, Piece> board, Color color) {
        int pawnCountInColumn = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            PositionValue columnValue = new PositionValue(i);
            long columnPawnCount = board.keySet().stream()
                .filter(point -> point.isSameColumn(columnValue))
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
            return (int)columnPawnCount;
        }
        return 0;
    }

    public double getScore() {
        return this.score;
    }

    public Color biggerScoreColor(Score whiteScore) {
        if (whiteScore.score > this.score) {
            return Color.WHITE;
        }
        if (whiteScore.score < this.score) {
            return Color.BLACK;
        }
        return Color.NOTHING;
    }
}
