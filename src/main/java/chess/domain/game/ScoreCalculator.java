package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreCalculator {

    private static final double SCORE_REDUCTION_RATE = 2.0;

    private final Map<Position, Piece> pieces;

    public ScoreCalculator(final Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public double calculate(final Color color) {
        final List<Pieces> piecesOfAllColumns = getPiecesOfAllColumns(color);
        return piecesOfAllColumns.stream()
                .mapToDouble(this::calculateOneColumn)
                .sum();
    }

    private List<Pieces> getPiecesOfAllColumns(final Color color) {
        final List<Pieces> result = new ArrayList<>();
        for (final Column column : Column.values()) {
            result.add(getPiecesOnColumn(column, color));
        }
        return result;
    }

    private double calculateOneColumn(final Pieces pieces) {
        final long pawnCount = pieces.getPawnCount();
        final double sum = pieces.getSumOfScore();

        if (pawnCount == 1) {
            return sum;
        }
        return sum - (pawnCount / SCORE_REDUCTION_RATE);
    }

    private Pieces getPiecesOnColumn(final Column column, final Color color) {
        final List<Piece> result = new ArrayList<>();
        for (final Row row : Row.values()) {
            final Piece piece = pieces.get(Position.of(column, row));
            result.add(piece);
        }
        final List<Piece> value = result.stream()
                .filter(piece -> piece.isSameColor(color))
                .collect(Collectors.toList());
        return new Pieces(value);
    }
}
