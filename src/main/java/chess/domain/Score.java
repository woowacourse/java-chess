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

    private static final double QUEEN_SCORE = 9.0;
    private static final double ROOK_SCORE = 5.0;
    private static final double BISHOP_SCORE = 3.0;
    private static final double KNIGHT_SCORE = 2.5;
    private static final int PAWN_INITIAL_SCORE = 1;
    private static final double PAWN_DUPLICATE_SCORE = 0.5;
    private static final int GRATER = 1;
    private static final int LESS = -1;
    private static final int EQUAL = 0;

    private static final Map<Piece, Double> scoreRuleByBlack = Map.of(
            new QueenPiece(Color.BLACK), QUEEN_SCORE,
            new RookPiece(Color.BLACK), ROOK_SCORE,
            new BishopPiece(Color.BLACK), BISHOP_SCORE,
            new KnightPiece(Color.BLACK), KNIGHT_SCORE
    );
    private static final Map<Piece, Double> scoreRuleByWhite = Map.of(
            new QueenPiece(Color.WHITE), QUEEN_SCORE,
            new RookPiece(Color.WHITE), ROOK_SCORE,
            new BishopPiece(Color.WHITE), BISHOP_SCORE,
            new KnightPiece(Color.WHITE), KNIGHT_SCORE
    );

    private final double value;
    private final Map<Piece, Double> scoreRuleWithoutPawn;

    public Score(final Board board, final Color color) {
        this.value = calculate(board, color);
        this.scoreRuleWithoutPawn = findScoreRuleWithoutPawnByColor(color);
    }

    private Map<Piece, Double> findScoreRuleWithoutPawnByColor(final Color color) {
        if (color.equals(Color.BLACK)) {
            return scoreRuleByBlack;
        }
        return scoreRuleByWhite;
    }

    private double calculate(final Board board, final Color color) {
        double score = calculateScoreWithoutPawn(board);
        score += calculateScorePawn(board, color);
        return score;
    }

    private double calculateScoreWithoutPawn(final Board board) {
        return scoreRuleWithoutPawn.entrySet()
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
        if (count == PAWN_INITIAL_SCORE) {
            return count;
        }
        return count * PAWN_DUPLICATE_SCORE;
    }

    public int decideResultValue(Score score) {
        if (value > score.value) {
            return GRATER;
        } else if (value < score.value) {
            return LESS;
        }
        return EQUAL;
    }

    public double getValue() {
        return value;
    }
}
