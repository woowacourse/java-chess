package chess.domain;

import chess.domain.piece.AbstractPiece;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Score {

    private static final double PENALTY_MULTIPLIER = 0.5;

    private final double value;

    private Score(double value) {
        this.value = value;
    }

    public static Score of(Map<Position, AbstractPiece> pieces) {
        double pawnsScore = calculatePawnScore(pieces);
        double scoreExceptPawn = calculateScoreExceptPawn(pieces);

        return new Score(pawnsScore + scoreExceptPawn);
    }

    private static double calculatePawnScore(Map<Position, AbstractPiece> pieces) {
        List<Position> pawnPositions = findPawnPositionsOf(pieces);
        Map<Row, List<Position>> pawnPositionsByRow = Position.groupByRow(pawnPositions);

        return pawnPositionsByRow.values()
                .stream()
                .mapToDouble(position -> calculateScoreOnSameRow(pieces, position))
                .sum();
    }

    private static List<Position> findPawnPositionsOf(Map<Position, AbstractPiece> pieces) {
        return pieces.keySet().stream()
                .filter(position -> pieces.get(position).isPawn())
                .collect(Collectors.toList());
    }

    private static double calculateScoreOnSameRow(Map<Position, AbstractPiece> pieces, List<Position> positions) {
        if (positions.size() > 1) {
            return positions.stream()
                    .mapToDouble(position -> pieces.get(position).score() * PENALTY_MULTIPLIER)
                    .sum();
        }
        return positions.stream()
                .mapToDouble(position -> pieces.get(position).score())
                .sum();
    }

    private static double calculateScoreExceptPawn(Map<Position, AbstractPiece> pieces) {
        return pieces.values()
                .stream()
                .filter(piece -> !piece.isPawn())
                .mapToDouble(AbstractPiece::score)
                .sum();
    }

    public boolean isOverThan(Score target) {
        return this.value > target.value;
    }

    public double getValue() {
        return value;
    }
}
