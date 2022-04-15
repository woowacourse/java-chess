package chess.dto;

import chess.model.piece.Color;
import chess.model.piece.Piece;
import chess.model.square.File;
import chess.model.square.Square;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ScoreResult {

    private static final int PAWN_SAME_LINE_COUNT = 2;
    private static final double PAWN_SAME_LINE_POINT = 0.5;

    private final Map<Color, Double> scoreResult;

    private ScoreResult(Map<Color, Double> scoreResult) {
        this.scoreResult = scoreResult;
    }

    public static ScoreResult from(Map<Square, Piece> board) {
        Map<Color, Double> scoreResult = Color.getPlayerColors().stream()
                .collect(Collectors.toMap(color -> color, color -> sumMajorPiecesPoint(board, color)));
        scoreResult.replaceAll((color, v) -> scoreResult.get(color) + pawnScore(collectPawns(board, color)));
        return new ScoreResult(scoreResult);
    }

    public Double get(Color color) {
        return scoreResult.get(color);
    }

    private static double pawnScore(Map<Square, Piece> whitePawns) {
        return Arrays.stream(File.values())
                .mapToInt(file -> file.countPawnsInSameFile(whitePawns.keySet()))
                .mapToDouble(ScoreResult::calculatePawnPoint)
                .sum();
    }

    private static Map<Square, Piece> collectPawns(Map<Square, Piece> board, Color color) {
        return board.keySet().stream()
                .filter(square -> board.get(square).isPawn() && board.get(square).isSameColor(color))
                .collect(Collectors.toMap(square -> square, board::get));
    }

    private static double sumMajorPiecesPoint(Map<Square, Piece> board, Color color) {
        return board.values().stream()
                .filter(piece -> !piece.isPawn() && piece.isSameColor(color))
                .mapToDouble(Piece::getPoint)
                .sum();
    }

    private static double calculatePawnPoint(int count) {
        if (count >= PAWN_SAME_LINE_COUNT) {
            return count * PAWN_SAME_LINE_POINT;
        }
        return count;
    }
}
