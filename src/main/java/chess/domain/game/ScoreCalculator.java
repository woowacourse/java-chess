package chess.domain.game;

import static chess.domain.position.File.FILES_TOTAL_SIZE;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScoreCalculator {

    private static final int PAWN_PENALTY_MINIMUM_COUNT = 2;

    private static final double SUM_BASE_DOUBLE = 0;

    public double calculate(List<Piece> sameColorPieces) {
        double defaultScore = calculateDefaultScore(sameColorPieces);
        double pawnPenaltyScore = calculatePawnPenalty(sameColorPieces);

        return defaultScore - pawnPenaltyScore;
    }

    private double calculatePawnPenalty(List<Piece> sameColorPieces) {
        List<Position> pawnPositions = extractPawnPositions(sameColorPieces);
        double penaltyPawnCount = calculateSameFilePawnCount(pawnPositions);

        return penaltyPawnCount * Pawn.DEFAULT_SCORE * Pawn.PENALTY_RATE;
    }

    private double calculateDefaultScore(List<Piece> sameColorPieces) {
        return sameColorPieces.stream()
            .map(Piece::getScore)
            .reduce(SUM_BASE_DOUBLE, Double::sum);
    }

    private double calculateSameFilePawnCount(List<Position> pawnPositions) {
        return IntStream.range(0, FILES_TOTAL_SIZE)
            .map(fileIdx -> extractSameFilePositionCount(pawnPositions, fileIdx))
            .filter(count -> count >= PAWN_PENALTY_MINIMUM_COUNT)
            .sum();
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
