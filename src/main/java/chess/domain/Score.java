package chess.domain;

import chess.domain.piece.BishopPiece;
import chess.domain.piece.Color;
import chess.domain.piece.KnightPiece;
import chess.domain.piece.PawnPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.QueenPiece;
import chess.domain.piece.RookPiece;
import chess.domain.position.File;
import java.util.Arrays;
import java.util.Map;

public class Score {

    private final double value;

    public Score(final Board board, final Color color) {
        this.value = calculate(board, color);
    }

    private double calculate(final Board board, final Color color) {
        final Map<Piece, Double> scoreRule = initializeScoreRuleWithoutPawn(color);

        double score = calculateScoreWithoutPawn(board, scoreRule);
        score += calculateScorePawn(board, color);

        return score;
    }

    private Map<Piece, Double> initializeScoreRuleWithoutPawn(final Color color) {
        return Map.of(
            new QueenPiece(color), 9.0,
            new RookPiece(color), 5.0,
            new BishopPiece(color), 3.0,
            new KnightPiece(color), 2.5
        );
    }

    private double calculateScoreWithoutPawn(final Board board,
        final Map<Piece, Double> pieceScores) {
        return pieceScores.entrySet()
            .stream()
            .mapToDouble(entry -> board.countPiece(entry.getKey()) * entry.getValue())
            .sum();
    }

    private double calculateScorePawn(final Board board, final Color color) {
        return Arrays.stream(File.values())
            .mapToInt(file -> board.countPieceOnSameFile(new PawnPiece(color), file))
            .mapToDouble(this::decidePawnScoreRule)
            .sum();
    }

    private double decidePawnScoreRule(final int count) {
        if (count == 1) {
            return count;
        }
        return count * 0.5;
    }

    public double getValue() {
        return value;
    }
}
