package chess.domain.game;

import chess.domain.location.Column;
import chess.domain.location.Position;
import chess.domain.location.Row;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Calculator {
    public Map<Color, Double> scoreByColor(Map<Position, Piece> maps) {
        Map<Color, Double> scores = new HashMap<>();
        scores.put(Color.BLACK, score(Color.BLACK, maps));
        scores.put(Color.WHITE, score(Color.WHITE, maps));
        return scores;
    }

    public double score(Color color, Map<Position, Piece> maps) {
        return totalScore(color, maps) - lowerPawnScore(color, maps);
    }

    private double totalScore(Color color, Map<Position, Piece> maps) {
        return Position.all()
                       .stream()
                       .map(maps::get)
                       .filter(piece -> piece.isSame(color))
                       .mapToDouble(Piece::score)
                       .sum();
    }

    public double lowerPawnScore(Color color, Map<Position, Piece> maps) {
        return Arrays.stream(Column.values())
                     .map(column -> countPawnBy(column, color, maps))
                     .filter(count -> count > 2)
                     .mapToDouble(number -> 0.5 * number)
                     .sum();
    }

    private long countPawnBy(Column column, Color color, Map<Position, Piece> maps) {
        return Arrays.stream(Row.values())
                     .filter(row -> maps.get(Position.of(column, row))
                                        .isSame(color))
                     .filter(row -> maps.get(Position.of(column, row))
                                        .isPawn())
                     .count();
    }
}
