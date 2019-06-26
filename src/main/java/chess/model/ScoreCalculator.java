package chess.model;

import chess.model.piece.Piece;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class ScoreCalculator {
    private static final double MIN_PAWN_IN_SAME_X_POSITION = 2;
    private static final double UPDATED_PAWN_SCORE = 0.5;
    private static final double INITIAL_PAWN_SCORE = 1;

    private List<Piece> pieces;

    public ScoreCalculator(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public double calculateScore(PlayerType team) {
        double sum = pieces.stream()
                .filter(piece -> piece.isSameTeam(team))
                .filter(piece -> !piece.isPawn())
                .mapToDouble(Piece::getScore)
                .sum();
        return sum + calculatePawnScore(team);
    }

    private double calculatePawnScore(PlayerType team) {
        Map<Integer, Long> xGroup = pieces.stream()
                .filter(piece -> piece.isSameTeam(team))
                .filter(Piece::isPawn)
                .collect(groupingBy(Piece::getX, counting()));

        return xGroup.values().stream()
                .mapToDouble(this::relativePawnScore)
                .sum();
    }

    private double relativePawnScore(long number) {
        if (number >= MIN_PAWN_IN_SAME_X_POSITION) {
            return number * UPDATED_PAWN_SCORE;
        }
        return INITIAL_PAWN_SCORE;
    }
}
