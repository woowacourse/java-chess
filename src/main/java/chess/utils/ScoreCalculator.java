package chess.utils;

import chess.domain.board.position.Position;
import chess.domain.board.position.Xpoint;
import chess.domain.piece.Piece;
import chess.domain.piece.team.Color;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreCalculator {
    private ScoreCalculator() {
    }

    public static double totalWhiteScore(List<Map<Position, Piece>> squares) {
        return scoreExceptPawns(squares, Color.WHITE) + scoreOfPawns(squares, Color.WHITE);
    }

    public static double totalBlackScore(List<Map<Position, Piece>> squares) {
        return scoreExceptPawns(squares, Color.BLACK) + scoreOfPawns(squares, Color.BLACK);
    }

    private static double scoreOfPawns(List<Map<Position, Piece>> squares, Color color) {
        double pawnScoreSum = 0;
        for (Xpoint xpoint : Xpoint.values()) {
            pawnScoreSum += verticalPawnScore(squares, xpoint, color);
        }
        return pawnScoreSum;
    }

    private static double verticalPawnScore(List<Map<Position, Piece>> squares, Xpoint xpoint, Color color) {
        long countOfPawnsInVertical = countOfPawnsInVertical(squares, xpoint, color);
        if (countOfPawnsInVertical > 1) {
            return countOfPawnsInVertical * 0.5;
        }
        return countOfPawnsInVertical;
    }

    private static long countOfPawnsInVertical(List<Map<Position, Piece>> squares, Xpoint xpoint, Color color) {
        List<Position> verticalPositions = verticalPositions(squares, xpoint);
        return squares.stream()
                .flatMap(map -> map.entrySet().stream())
                .filter(entry -> verticalPositions.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .filter(piece -> piece.isSameColor(color))
                .filter(Piece::isPawn)
                .count();
    }

    private static double scoreExceptPawns(List<Map<Position, Piece>> squares, Color color) {
        return squares.stream()
                .flatMap(map -> map.values().stream())
                .filter(piece -> piece.isSameColor(color))
                .filter(piece -> !piece.isPawn())
                .mapToDouble(Piece::score)
                .sum();
    }

    private static List<Position> verticalPositions(List<Map<Position, Piece>> squares, Xpoint xpoint) {
        return squares.stream()
                .flatMap(map -> map.keySet().stream())
                .filter(position -> position.isSameX(xpoint))
                .collect(Collectors.toList());
    }
}
