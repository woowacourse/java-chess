package domain.chessGame;

import domain.piece.Piece;
import domain.position.Position;

import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreCalculator {
    private static final List<Integer> ORDERED_COLUMNS = List.of(1, 2, 3, 4, 5, 6, 7, 8);

    private double blackScore;
    private double whiteScore;

    public ScoreCalculator(Map<Position, Piece> blackPieces, Map<Position, Piece> whitePieces) {
        saveScore(blackPieces, whitePieces);
    }

    public void saveScore(Map<Position, Piece> blackPieces, Map<Position, Piece> whitePieces) {
        blackScore = calculateScore(blackPieces);
        whiteScore = calculateScore(whitePieces);
    }

    private double calculateScore(Map<Position, Piece> pieces) {
        double sumOfScore = getScoreSum(pieces);
        List<Position> pawnPositions = getOnlyPawnPositions(pieces);

        for (int index : ORDERED_COLUMNS) {
            int countOfSameColumnPawn = getCountOfSameColumnPawn(pawnPositions, index);
            sumOfScore = subtractDuplicatedPawnScore(sumOfScore, countOfSameColumnPawn);
        }

        return sumOfScore;
    }

    private double subtractDuplicatedPawnScore(double sumOfScore, int countOfSameColumnPawn) {
        if (countOfSameColumnPawn > 1) {
            sumOfScore -= 0.5 * countOfSameColumnPawn;
        }
        return sumOfScore;
    }

    private int getCountOfSameColumnPawn(List<Position> pawnPositions, int index) {
        return (int) pawnPositions.stream()
                .filter(position -> position.isSameColumn(index))
                .count();
    }

    private List<Position> getOnlyPawnPositions(Map<Position, Piece> pieces) {
        return pieces.entrySet().stream()
                .filter(entry -> entry.getValue().isPawn())
                .map(Entry::getKey)
                .collect(Collectors.toList());
    }

    private double getScoreSum(Map<Position, Piece> pieces) {
        return pieces.values().stream()
                .mapToDouble(Piece::getScore)
                .sum();
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }
}
