package chess.domain;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Square;

public final class GameResult {
    private static final double PAWN_PENALTY_SCORE = 0.5;
    private final Board board;

    public GameResult(Board board) {
        this.board = board;
    }

    public double calculateScore(Color color) {
        List<Map.Entry<Square, Piece>> survives = board.filterBy(color);
        return adjustSum(getSum(survives), survives);
    }

    private double getSum(List<Map.Entry<Square, Piece>> survives) {
        return survives.stream()
                .map(Map.Entry::getValue)
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private double adjustSum(double sum, List<Map.Entry<Square, Piece>> survives) {
        for (Column column : Column.values()) {
            int count = countPawnInSameFile(survives, column);
            sum = subtractPawnInSameFile(sum, count);
        }
        return sum;
    }

    private int countPawnInSameFile(List<Map.Entry<Square, Piece>> survives, Column column) {
        return (int) survives.stream()
                .filter(hasPawnInSamFile(column))
                .count();
    }

    private Predicate<Map.Entry<Square, Piece>> hasPawnInSamFile(Column column) {
        return entry -> entry.getValue().isPawn() && entry.getKey().checkFile(column);
    }

    private double subtractPawnInSameFile(double sum, int count) {
        if (count > 1) {
            sum -= PAWN_PENALTY_SCORE * count;
        }
        return sum;
    }
}
