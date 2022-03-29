package chess.game;

import static chess.piece.Color.BLACK;
import static chess.piece.Color.WHITE;

import chess.piece.Color;
import chess.piece.Pawn;
import chess.piece.Piece;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class Score {

    private static final int PAWN_MINIMUM_REDUCE_ROW_COUNT = 1;

    private final Map<Color, Double> score = new EnumMap<>(Color.class);

    public void calculate(final Map<Position, Piece> board) {
        score.put(BLACK, calculateScore(board, BLACK));
        score.put(WHITE, calculateScore(board, WHITE));
    }

    public double calculateScore(final Map<Position, Piece> board, final Color color) {
        return sumScore(board, color) - pawnCountOnSameColumn(board, color) * Pawn.REDUCED_SCORE;
    }

    private double sumScore(final Map<Position, Piece> board, final Color color) {
        return board.values().stream()
                .filter(piece -> piece.isEqualColor(color))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private double pawnCountOnSameColumn(final Map<Position, Piece> board, final Color color) {
        return Arrays.stream(Column.values())
                .mapToInt(column -> countPawnsByColumn(board, column.getValue(), color))
                .filter(count -> count > PAWN_MINIMUM_REDUCE_ROW_COUNT)
                .sum();
    }

    private int countPawnsByColumn(final Map<Position, Piece> board, final int column, final Color color) {
        return (int) board.keySet().stream()
                .filter(position -> position.equalsColumn(column))
                .map(board::get)
                .filter(piece -> piece.isPawn() && piece.isEqualColor(color))
                .count();
    }

    public Map<Color, Double> getScore() {
        return new EnumMap<>(score);
    }
}
