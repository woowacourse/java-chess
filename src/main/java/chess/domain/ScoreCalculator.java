package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.PositionUtil;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScoreCalculator {

    private static final int PAWN_PENALTY_MINIMUM_COUNT = 2;

    private static final int SUM_BASE_INT = 0;
    private static final double SUM_BASE_DOUBLE = 0;
    private static final double NO_PAWN_PENALTY_SCORE = 0;

    private static final double PAWN_PENALTY_RATE = 0.5;

    public double calculate(List<Piece> sameColorPieces) {
        double defaultScore = calculateDefaultScore(sameColorPieces);
        double pawnPenaltyScore = calculatePawnPenalty(sameColorPieces);

        return defaultScore - pawnPenaltyScore;
    }

    private double calculatePawnPenalty(List<Piece> sameColorPieces) {
        List<Position> pawnPositions = extractPawnPositions(sameColorPieces);

        double penaltyPawnCount = calculateSameFilePawnCount(pawnPositions);
        double pawnScore = sameColorPieces.stream()
            .filter(Piece::isPawn)
            .map(Piece::score)
            .findAny()
            .orElse(NO_PAWN_PENALTY_SCORE);

        return pawnScore * penaltyPawnCount * PAWN_PENALTY_RATE;
    }

    private double calculateDefaultScore(List<Piece> sameColorPieces) {
        return sameColorPieces.stream()
            .map(Piece::score)
            .reduce(SUM_BASE_DOUBLE, Double::sum);
    }

    private double calculateSameFilePawnCount(List<Position> pawnPositions) {
        return IntStream.range(0, PositionUtil.FILES_TOTAL_SIZE)
            .map(fileIdx -> extractSameFilePositionCount(pawnPositions, fileIdx))
            .filter(count -> count >= PAWN_PENALTY_MINIMUM_COUNT)
            .reduce(SUM_BASE_INT, Integer::sum);
    }

    private List<Position> extractPawnPositions(List<Piece> sameColorPieces) {
        return sameColorPieces.stream()
            .filter(Piece::isPawn)
            .map(Piece::getPosition)
            .collect(Collectors.toUnmodifiableList());
    }

    private int extractSameFilePositionCount(List<Position> positions, int fileIdx) {
        return (int) positions.stream()
            .filter(position -> position.hasSameFileIdx(fileIdx))
            .count();
    }

}
