package chess.domain;

import chess.domain.pieces.Piece;
import chess.domain.pieces.PieceInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessResult {
    public static final String TEAM = "team";
    public static final String SCORE = "score";
    private static final double PAWN_BALANCE_POINT = 0.5;
    private final Map<Point, Piece> result;
    private final ChessTeam team;

    ChessResult(Map<Point, Piece> result, ChessTeam team) {
        this.result = result;
        this.team = team;
    }

    public double score() {
        double sum = result.values().stream()
                .mapToDouble(Piece::score)
                .sum();
        return balanceInLinePawn(sum);
    }

    private double balanceInLinePawn(double sum) {
        return sum - result.entrySet().stream()
                .filter(entry -> entry.getValue().score() == PieceInfo.Pawn.score())
                .collect(Collectors.groupingBy(entry -> entry.getKey().getX(), Collectors.counting()))
                .values().stream()
                .filter(value -> value > 1)
                .mapToDouble(value -> value * PAWN_BALANCE_POINT)
                .sum();
    }

    public Map<String, String> status() {
        Map<String, String> status = new HashMap<>();
        status.put(TEAM, team.name());
        status.put(SCORE, String.valueOf(score()));
        return status;
    }
}