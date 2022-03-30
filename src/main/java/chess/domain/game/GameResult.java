package chess.domain.game;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;

public class GameResult {

    private static final int ALLOWED_ONE_LINE_PAWN_COUNT = 2;
    private static final double PAWN_PENALTY_RATE = 0.5;

    private GameResult() {
    }

    public static Map<Color, Double> calculateTotalScore(Map<Position, Piece> boardPieces) {
        Map<Color, Double> score = new HashMap<>();
        score.put(Color.BLACK, calculateColorScore(boardPieces, Color.BLACK));
        score.put(Color.WHITE, calculateColorScore(boardPieces, Color.WHITE));
        return score;
    }

    private static double calculateColorScore(Map<Position, Piece> boardPieces, Color color) {
        return boardPieces.values().stream()
            .filter(piece -> piece.isSameColor(color))
            .mapToDouble(Piece::getScore)
            .sum() - calculateSameLinePawnSubtraction(boardPieces, color);
    }

    private static double calculateSameLinePawnSubtraction(Map<Position, Piece> boardPieces, Color color) {
        List<File> pawnFiles = boardPieces.keySet().stream()
            .filter(position -> boardPieces.get(position).isPawn() && boardPieces.get(position).isSameColor(color))
            .map(Position::getFile)
            .collect(Collectors.toList());

        return new HashSet<>(pawnFiles).stream()
            .filter(file -> Collections.frequency(pawnFiles, file) >= ALLOWED_ONE_LINE_PAWN_COUNT)
            .mapToDouble(file -> Collections.frequency(pawnFiles, file) * PAWN_PENALTY_RATE)
            .sum();
    }
}
