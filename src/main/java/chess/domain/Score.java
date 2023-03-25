package chess.domain;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import chess.domain.piece.Piece;
import java.util.Map;

public class Score {
    private final double score;

    private Score(double score) {
        this.score = score;
    }

    public static Score of(Map<Position, Piece> squares) {
        double withoutPawnScore = getScoreWithoutPawn(squares);
        double pawnScore = getPawnScore(squares);
        return new Score(withoutPawnScore + pawnScore);
    }

    private static double getScoreWithoutPawn(Map<Position, Piece> squares) {
        return squares.values().stream()
                .filter(piece -> !piece.isRoleOf(Role.PAWN))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private static double getPawnScore(Map<Position, Piece> squares) {
        Map<Integer, Long> pawnCountByX = getPawnCountByX(squares);
        return pawnCountByX.values().stream()
                .mapToDouble(Score::calculatePawnScore)
                .sum();
    }

    private static Map<Integer, Long> getPawnCountByX(Map<Position, Piece> squares) {
        return squares.entrySet().stream()
                .filter(entry -> entry.getValue().isRoleOf(Role.PAWN))
                .collect(groupingBy(entry -> entry.getKey().getX(), counting()));
    }

    private static double calculatePawnScore(Long pawnCount) {
        if (pawnCount == 1) {
            return Role.PAWN.getScore();
        }
        return pawnCount * Role.PAWN.getScore() / 2.0;
    }

    public double getScore() {
        return score;
    }
}
