package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import util.NullChecker;

public class TeamScore {

    private static final double PAWN_SAME_FILE_SCORE = -0.5;
    private final Map<Color, Double> teamScore;

    public TeamScore(Collection<Piece> pieces, Map<Color, Integer> pawnSameFileCountByColor) {
        NullChecker.validateNotNull(pieces, pawnSameFileCountByColor);
        this.teamScore = Collections
            .unmodifiableMap(getTeamScore(pieces, pawnSameFileCountByColor));
    }

    private Map<Color, Double> getTeamScore(Collection<Piece> pieces,
        Map<Color, Integer> pawnSameFileByColor) {
        Map<Color, Double> teamScore = new HashMap<>();
        for (Color color : Color.values()) {
            double piecesSumScore = getSumScore(pieces, color);
            double pawnChargeScore = pawnSameFileByColor.get(color) * PAWN_SAME_FILE_SCORE;
            teamScore.put(color, piecesSumScore + pawnChargeScore);
        }
        return teamScore;
    }

    private double getSumScore(Collection<Piece> pieces, Color color) {
        return pieces.stream()
            .filter(piece -> piece.isSameColor(color))
            .mapToDouble(Piece::getScore)
            .sum();
    }

    public List<Color> getWinners() {
        return teamScore.keySet().stream()
            .filter(color -> teamScore.get(color) == getWinningScore())
            .collect(Collectors.toList());
    }

    private double getWinningScore() {
        return teamScore.values().stream()
            .max(Double::compareTo)
            .orElseThrow(IllegalAccessError::new);
    }

    public Map<Color, Double> getTeamScore() {
        return teamScore;
    }
}
