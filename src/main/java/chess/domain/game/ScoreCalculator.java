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

public class ScoreCalculator {

    private static final int ALLOWED_ONE_LINE_PAWN_COUNT = 2;
    private static final double PAWN_PENALTY_RATE = 0.5;

    Map<Position, Piece> boardPieces;

    public ScoreCalculator(Map<Position, Piece> boardPieces) {
        this.boardPieces = new HashMap<>(boardPieces);
    }

    public Map<Color, Double> calculateAllTeamScore() {
        Map<Color, Double> score = new HashMap<>();
        score.put(Color.BLACK, calculateScore(Color.BLACK));
        score.put(Color.WHITE, calculateScore(Color.WHITE));
        return score;
    }

    private double calculateScore(Color color) {
        return boardPieces.values().stream()
            .filter(piece -> piece.isSameColor(color))
            .mapToDouble(Piece::getScore)
            .sum() - calculateSameLinePawnSubtraction(color);
    }

    private double calculateSameLinePawnSubtraction(Color color) {
        List<File> pawnFiles = getPawnFiles(color);

        return new HashSet<>(pawnFiles).stream()
            .filter(file -> Collections.frequency(pawnFiles, file) >= ALLOWED_ONE_LINE_PAWN_COUNT)
            .mapToDouble(file -> Collections.frequency(pawnFiles, file) * PAWN_PENALTY_RATE)
            .sum();
    }

    private List<File> getPawnFiles(Color color) {
        return boardPieces.keySet().stream()
            .filter(position -> boardPieces.get(position).isPawn() && boardPieces.get(position).isSameColor(color))
            .map(Position::getFile)
            .collect(Collectors.toList());
    }
}
