package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Score {

    private static final double PENALTY_MULTIPLIER = 0.5;
    private static final int PENALTY_SIZE_STANDARD = 1;

    private final double value;

    private Score(double value) {
        this.value = value;
    }

    public static Score of(Map<Position, Piece> pieces) {
        if (hasKing(pieces)) {
            double pawnsScore = calculatePawnScore(pieces);
            double scoreExceptPawn = calculateScoreExceptPawn(pieces);

            return new Score(pawnsScore + scoreExceptPawn);
        }
        return new Score(0);
    }

    private static boolean hasKing(Map<Position, Piece> pieces) {
        return pieces.values().stream().anyMatch(Piece::isKing);
    }

    private static double calculatePawnScore(Map<Position, Piece> pieces) {
        List<Position> pawnPositions = findPawnPositionsOf(pieces);
        Map<Row, List<Position>> pawnPositionsByRow = Position.groupByRow(pawnPositions);

        return pawnPositionsByRow.values()
                .stream()
                .mapToDouble(position -> calculateScoreOnSameRow(pieces, position))
                .sum();
    }

    private static List<Position> findPawnPositionsOf(Map<Position, Piece> pieces) {
        return pieces.keySet()
                .stream()
                .filter(position -> pieces.get(position).isPawn())
                .collect(Collectors.toList());
    }

    private static double calculateScoreOnSameRow(Map<Position, Piece> pieces, List<Position> positions) {
        if (positions.size() > PENALTY_SIZE_STANDARD) {
            return positions.stream()
                    .mapToDouble(position -> pieces.get(position).score() * PENALTY_MULTIPLIER)
                    .sum();
        }
        return positions.stream()
                .mapToDouble(position -> pieces.get(position).score())
                .sum();
    }

    private static double calculateScoreExceptPawn(Map<Position, Piece> pieces) {
        return pieces.values()
                .stream()
                .filter(piece -> !piece.isPawn())
                .mapToDouble(Piece::score)
                .sum();
    }

    public boolean isOverThan(Score target) {
        return this.value > target.value;
    }

    public double getValue() {
        return value;
    }
}
