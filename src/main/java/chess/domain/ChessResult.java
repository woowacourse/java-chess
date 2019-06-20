package chess.domain;

import chess.domain.pieces.Piece;

import java.util.Map;
import java.util.stream.Collectors;

public class ChessResult {
    private final Map<Point, Piece> result;

    public ChessResult(Map<Point, Piece> result) {
        this.result = result;
    }

    public double score() {
        double sum = result.values().stream()
                .mapToDouble(Piece::score)
                .sum();
        return balanceInLinePawn(sum);
    }

    private double balanceInLinePawn(double sum) {
        return sum - result.entrySet().stream()
                .filter(entry -> entry.getValue().score() == 1)
                .collect(Collectors.groupingBy(entry -> entry.getKey().getX(), Collectors.counting()))
                .values().stream()
                .filter(value -> value > 1)
                .mapToDouble(value -> value * 0.5)
                .sum();
    }
}