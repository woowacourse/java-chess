package chess.game;

import static chess.piece.Color.BLACK;
import static chess.piece.Color.WHITE;

import chess.piece.Color;
import chess.piece.Pawn;
import chess.piece.Piece;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Score {

    private static final int PAWN_MINIMUM_REDUCE_ROW_COUNT = 1;

    private final Map<Color, Double> score;
    private final Map<Position, Piece> board;

    public Score(final Map<Position, Piece> board) {
        this.score = new EnumMap<>(Color.class);
        this.board = new HashMap<>(board);
        calculate();
    }

    private void calculate() {
        score.put(BLACK, calculateScore(BLACK));
        score.put(WHITE, calculateScore(WHITE));
    }

    private double calculateScore(final Color color) {
        return sumScore(color) - pawnCountOnSameColumn(color) * Pawn.REDUCED_SCORE;
    }

    private double sumScore(final Color color) {
        return board.values().stream()
                .filter(piece -> piece.isEqualColor(color))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private double pawnCountOnSameColumn(final Color color) {
        return Arrays.stream(Column.values())
                .mapToInt(column -> countPawnsByColumn(column.getValue(), color))
                .filter(count -> count > PAWN_MINIMUM_REDUCE_ROW_COUNT)
                .sum();
    }

    private int countPawnsByColumn(final int column, final Color color) {
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
