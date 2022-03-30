package chess.domain.result;

import chess.domain.Board;
import chess.domain.piece.BishopPiece;
import chess.domain.piece.Color;
import chess.domain.piece.KnightPiece;
import chess.domain.piece.PawnPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.QueenPiece;
import chess.domain.piece.RookPiece;
import chess.domain.position.Column;
import java.util.Arrays;
import java.util.Map;

public class Score {

    private static final double QUEEN_SCORE = 9.0;
    private static final double ROOK_SCORE = 5.0;
    private static final double BISHOP_SCORE = 3.0;
    private static final double KNIGHT_SCORE = 2.5;
    private static final int PAWN_INITIAL_SCORE = 1;
    private static final double PAWN_DUPLICATE_SCORE = 0.5;

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
                new QueenPiece(color), QUEEN_SCORE,
                new RookPiece(color), ROOK_SCORE,
                new BishopPiece(color), BISHOP_SCORE,
                new KnightPiece(color), KNIGHT_SCORE
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
        return Arrays.stream(Column.values())
                .mapToInt(file -> board.countPieceOnSameColumn(new PawnPiece(color), file))
                .mapToDouble(this::decidePawnScoreRule)
                .sum();
    }

    private double decidePawnScoreRule(final int count) {
        if (count == PAWN_INITIAL_SCORE) {
            return count;
        }
        return count * PAWN_DUPLICATE_SCORE;
    }

    public Result decideResult(final Score score) {
        if (value > score.value) {
            return Result.WIN;
        } else if (value < score.value) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    public double getValue() {
        return value;
    }
}
