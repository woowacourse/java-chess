package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ScoreCalculator {

    private static final double SAME_FILE_PAWN_MINUS_SCORE = 0.5;
    private static final int MINIMUM_SCORE_MINUS_COUNT = 1;
    private final Map<Position, Piece> pieces;

    public ScoreCalculator(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public Map<Color, Double> calculateScore() {
        return Arrays.stream(Color.values())
                .collect(Collectors.toMap(Function.identity(), this::calculateScore));
    }

    private double calculateScore(Color color) {
        double defaultScore = calculateDefaultScore(color);
        return defaultScore - sameFilePawnScore(color);
    }

    private double calculateDefaultScore(Color color) {
        return pieces.values()
                .stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::getScore)
                .sum();
    }


    private double sameFilePawnScore(Color color) {
        return Arrays.stream(File.values())
                .mapToInt(file -> countSameFilePawn(color, file))
                .filter(count -> count > MINIMUM_SCORE_MINUS_COUNT)
                .mapToDouble(count -> SAME_FILE_PAWN_MINUS_SCORE * (count - MINIMUM_SCORE_MINUS_COUNT))
                .sum();
    }

    private int countSameFilePawn(Color color, File file) {
        return (int) Arrays.stream(Rank.values())
                .map(rank -> pieces.get(Position.of(file, rank)))
                .filter(piece -> piece.getType() == PieceType.PAWN && piece.getColor() == color)
                .count();
    }
}
