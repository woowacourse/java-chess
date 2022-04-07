package chess.domain.score;

import chess.domain.ChessScore;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ScoreCalculator {

    public static ChessScore calculateChessScore(Map<Position, Piece> pieces) {
        return new ChessScore(generateNotPawnScore(pieces, Color.WHITE) + generatePawnScore(pieces, Color.WHITE),
                generateNotPawnScore(pieces, Color.BLACK) + generatePawnScore(pieces, Color.BLACK));
    }

    private static double generateNotPawnScore(Map<Position, Piece> pieces, Color color) {
        return pieces.values().stream()
                .filter(each -> !each.isPawn() && each.isSameColor(color))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private static double generatePawnScore(Map<Position, Piece> pieces, Color color) {
        double pawnScore = 0.0;
        for (int i = Position.MIN; i <= Position.MAX; i++) {
            pawnScore += calculatePawnScore(pieces, i, color);
        }
        return pawnScore;
    }

    private static double calculatePawnScore(Map<Position, Piece> pieces, int nowColumn, Color color) {
        double count = pieces.keySet().stream()
                .filter(each -> each.isSameColumn(nowColumn) && pieces.get(each).isPawn() && pieces.get(each)
                        .isSameColor(color))
                .mapToDouble(each -> pieces.get(each).getScore())
                .sum();
        if (count > 1) {
            return count * 0.5;
        }
        return count;
    }
}
