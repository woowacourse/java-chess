package chess.model.util;

import chess.model.piece.Color;
import chess.model.piece.Piece;
import chess.model.square.File;
import chess.model.square.Square;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ScoreResult {

    private final Map<Color, Double> scoreResult;

    private static final String CANNOT_FOUND_WINNER_ERROR_MESSAGE = "우승자를 찾을 수 없습니다.";
    private static final String DRAW_MESSAGE = "무승부";
    private static final String WHITE_WIN = "WHITE 승";
    private static final String BLACK_WIN = "BLACK 승";

    private ScoreResult(Map<Color, Double> scoreResult) {
        this.scoreResult = scoreResult;
    }

    public static ScoreResult of(Map<Square, Piece> board) {
        Map<Color, Double> scoreResult = Arrays.stream(Color.values())
                .collect(Collectors.toMap(color -> color, color -> sumChivalryPoint(board, color)));
        for (Color color : Color.values()) {
            scoreResult.put(color, scoreResult.get(color) + pawnScore(collectPawns(board, color)));
        }
        return new ScoreResult(scoreResult);
    }

    public String findWinnerName() {
        final double subtractedScore = Color.getPlayerColors().stream()
                .mapToDouble(scoreResult::get)
                .reduce((x, y) -> x - y)
                .orElseThrow(() -> new IllegalArgumentException(CANNOT_FOUND_WINNER_ERROR_MESSAGE));
        return findWinner(subtractedScore);
    }

    public Set<Color> keySet() {
        return scoreResult.keySet();
    }

    public Double get(Color color) {
        return scoreResult.get(color);
    }

    private String findWinner(final Double subtractedScore) {
        if (subtractedScore.equals(0.0)) {
            return DRAW_MESSAGE;
        }
        if (subtractedScore < 0) {
            return BLACK_WIN;
        }
        return WHITE_WIN;
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


    private static double sumChivalryPoint(Map<Square, Piece> board, Color color) {
        return board.values().stream()
                .filter(piece -> !piece.isPawn() && piece.isSameColor(color))
                .mapToDouble(Piece::getPoint)
                .sum();
    }

    private static double calculatePawnPoint(int count) {
        if (count >= 2) {
            return count * 0.5;
        }
        return count;
    }
}
