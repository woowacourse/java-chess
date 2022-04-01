package chess.domain.result;

import chess.domain.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import java.util.Arrays;
import java.util.List;

public class Score {

    private static final int PAWN_DEFAULT_COUNT = 1;
    private static final double PAWN_SCORE_DIVISOR = 2.0;

    private final double value;

    public Score(final Board board, final Color color) {
        this.value = calculate(board, color);
    }

    private double calculate(final Board board, final Color color) {
        double score = calculateScoreWithoutPawn(board, color);
        score += calculateScorePawn(board, color);

        return score;
    }

    private double calculateScoreWithoutPawn(final Board board, final Color color) {
        return board.findPieceNotPawn(color)
                .stream()
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private double calculateScorePawn(final Board board, final Color color) {
        return Arrays.stream(Column.values())
                .map(column -> board.findPawnOnSameColumn(color, column))
                .map(this::calculateScorePawnOnSameColumn)
                .mapToDouble(this::decidePawnScoreRule)
                .sum();
    }

    private double calculateScorePawnOnSameColumn(final List<Piece> pieces) {
        return pieces
                .stream()
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private double decidePawnScoreRule(final double score) {
        if (score > PAWN_DEFAULT_COUNT) {
            return score / PAWN_SCORE_DIVISOR;
        }
        return score;
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
