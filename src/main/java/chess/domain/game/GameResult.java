package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Column;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class GameResult {
    private static final int SAME_COLUMN_CRITERIA = 2;
    private static final double SAME_COLUMN_SCORE = 0.5;

    private final Map<Color, Double> score = new HashMap<>();
    private final Pieces pieces;

    public GameResult(final Pieces pieces) {
        this.pieces = pieces;
        score.put(Color.BLACK, score(Color.BLACK));
        score.put(Color.WHITE, score(Color.WHITE));
    }

    public double score(final Color color) {
        return calculateGeneralScore(color) + calculatePawnScore(color);
    }

    private double calculateGeneralScore(final Color color) {
        return pieces.getPieces().stream()
                .filter(piece -> piece.isSameColor(color))
                .filter(piece -> !piece.isPawn())
                .mapToDouble(Piece::score)
                .sum();
    }

    private double calculatePawnScore(final Color color) {
        return pawnCountingByColumn(color).values()
                .stream()
                .mapToDouble(this::lowerPawnScore)
                .sum();
    }

    private Map<Column, Long> pawnCountingByColumn(final Color color) {
        return pawnsWith(color).stream()
                .collect(groupingBy(Piece::getColumn, counting()));
    }

    private List<Piece> pawnsWith(final Color color) {
        return pieces.getPieces().stream()
                .filter(piece -> piece.isSameColor(color))
                .filter(Piece::isPawn)
                .collect(Collectors.toList());
    }

    private double lowerPawnScore(final long count) {
        if (count >= SAME_COLUMN_CRITERIA) {
            return count * SAME_COLUMN_SCORE;
        }
        return count;
    }

    public Map<Color, Double> getScore() {
        return score;
    }
}
