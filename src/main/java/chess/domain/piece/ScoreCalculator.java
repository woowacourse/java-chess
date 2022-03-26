package chess.domain.piece;

import static chess.domain.piece.position.PositionUtil.FILES_TOTAL_SIZE;

import chess.domain.piece.position.Position;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScoreCalculator {

    private static final int PAWN_PENALTY_MINIMUM_COUNT = 2;

    private static final int SUM_BASE_INT = 0;
    private static final double SUM_BASE_DOUBLE = 0;


    public double calculate(List<Piece> sameColorPieces) {
        List<Position> pawnPositions = extractPawnPositions(sameColorPieces);

        double defaultScore = sameColorPieces.stream()
            .map(Piece::score)
            .reduce(SUM_BASE_DOUBLE, Double::sum);

        return defaultScore - (calculateSameFilePawnCount(pawnPositions) / PAWN_PENALTY_MINIMUM_COUNT);
    }

    private double calculateSameFilePawnCount(List<Position> pawnPositions) {
        return IntStream.range(0, FILES_TOTAL_SIZE)
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
