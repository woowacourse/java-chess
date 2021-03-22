package chess.domain;

import chess.domain.piece.Pieces;

import java.util.EnumMap;
import java.util.Map;

import static chess.domain.position.Position.Xs;

public class ScoreStatus {
    public static final int PAWN_SUBTRACT_COUNT_PIVOT = 2;
    private final Map<Color, Double> totalScoreByColor;

    public ScoreStatus(Map<Color, Double> totalScoreByColor) {
        this.totalScoreByColor = totalScoreByColor;
    }

    public static ScoreStatus generateByColor(Pieces pieces) {
        Map<Color, Double> totalScoreByColor = new EnumMap<>(Color.class);
        totalScoreByColor.put(Color.BLACK, totalScoreByColorWithRule(pieces, Color.BLACK));
        totalScoreByColor.put(Color.WHITE, totalScoreByColorWithRule(pieces, Color.WHITE));
        return new ScoreStatus(totalScoreByColor);
    }

    private static double totalScoreByColorWithRule(Pieces pieces, Color color) {
        Pieces piecesByColor = pieces.piecesByColor(color);
        Score totalScore = piecesByColor.totalScore();
        int sameXPawnCount = Xs.chars()
                .map(index -> piecesByColor.pawnCountByX((char) index))
                .filter(count -> count >= PAWN_SUBTRACT_COUNT_PIVOT)
                .sum();
        return totalScore.subtractedByMultipliedCount(sameXPawnCount);
    }

    public Map<Color, Double> totalScoreByColor() {
        return totalScoreByColor;
    }
}
