package chess.domain.game;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class GameResult {

    private static final double DUPLICATED_PAWN_DEDUCTION_SCORE = 0.5;

    private static final long INITIAL_KING_COUNT = 2;

    private final Map<Position, Piece> positionToPiece;

    public GameResult(Map<Position, Piece> positionToPiece) {
        this.positionToPiece = positionToPiece;
    }

    public Score getScore(Color color) {
        double pieceScore = calculatePieceScore(color);
        double deductionScore = calculateDeductionScore(color);
        double score = pieceScore - deductionScore;
        return Score.valueOf(score);
    }

    private double calculatePieceScore(Color color) {
        return positionToPiece.values().stream()
                .filter(piece -> piece.getColor() == color)
                .map(piece -> piece.getScore().getValue())
                .mapToDouble(i -> i)
                .sum();
    }

    private double calculateDeductionScore(Color color) {
        Map<Integer, List<Piece>> fileToPawn = positionToPiece.entrySet().stream()
                .filter(it -> it.getValue().getColor() == color && it.getValue().isSameType(PieceType.PAWN))
                .collect(groupingBy(it -> it.getKey().getFileIndex(),
                        mapping(Entry::getValue, Collectors.toList())));

        int duplicatedPawnSize = fileToPawn.values().stream()
                .filter(it -> it.size() > 1)
                .mapToInt(List::size)
                .sum();
        return duplicatedPawnSize * DUPLICATED_PAWN_DEDUCTION_SCORE;
    }

    public Color getWinner() {
        if (isGameOver()) {
            return getWinnerOfEnd();
        }
        return getCurrentWinner(getScore(Color.BLACK), getScore(Color.WHITE));
    }

    private boolean isGameOver() {
        long kingCount = positionToPiece.values().stream()
                .filter(piece -> piece.isSameType(PieceType.KING))
                .count();
        return kingCount != INITIAL_KING_COUNT;
    }

    private Color getWinnerOfEnd() {
        Piece king = positionToPiece.values().stream()
                .filter(piece -> piece.isSameType(PieceType.KING))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("살아있는 왕이 없습니다."));
        return king.getColor();
    }

    private Color getCurrentWinner(Score blackScore, Score whiteScore) {
        if (blackScore.getValue() > whiteScore.getValue()) {
            return Color.BLACK;
        }
        if (blackScore.getValue() < whiteScore.getValue()) {
            return Color.WHITE;
        }
        return Color.NONE;
    }
}
