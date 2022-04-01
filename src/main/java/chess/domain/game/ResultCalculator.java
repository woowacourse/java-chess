package chess.domain.game;

import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.machine.Result;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ResultCalculator {

    private static final double PAWN_PENALTY_SCORE = 0.5;
    private static final int SINGLE_COUNT = 1;

    public static double calculateScore(final Board board, final Color color) {
        return calculateDefaultScore(board, color) - countPawnsOnSameColumns(board, color) * PAWN_PENALTY_SCORE;
    }

    private static double calculateDefaultScore(final Board board, final Color color) {
        return board.existPieces()
                .stream()
                .filter(piece -> piece.isSameColor(color))
                .mapToDouble(Piece::score)
                .sum();
    }

    private static int countPawnsOnSameColumns(final Board board, final Color color) {
        return Arrays.stream(Column.values())
                .mapToInt(column -> countPawnsOnSameColumn(board, column, color))
                .filter(count -> count > SINGLE_COUNT)
                .sum();
    }

    private static int countPawnsOnSameColumn(final Board board, final Column column, final Color color) {
        return (int) Arrays.stream(Row.values())
                .map(row -> board.piece(Position.valueOf(column, row)))
                .filter(piece -> piece.isPresent() && piece.get().isPawn() && piece.get().isSameColor(color))
                .count();
    }

    public static Map<Result, Color> calculateScoreWinner(final Board board) {
        Map<Result, Color> gameResult = new HashMap<>();
        if (calculateScore(board, Color.WHITE) > calculateScore(board, Color.BLACK)) {
            gameResult.put(Result.WIN, Color.WHITE);
        }
        if (calculateScore(board, Color.WHITE) < calculateScore(board, Color.BLACK)) {
            gameResult.put(Result.WIN, Color.BLACK);
        }
        return gameResult;
    }

    public static Map<Result, Color> calculateFinalWinner(final Board board) {
        if (board.isKingAlive(Color.WHITE) && board.isKingAlive(Color.BLACK)) {
            return calculateScoreWinner(board);
        }
        return calculateWinnerWithKing(board);
    }

    private static Map<Result, Color> calculateWinnerWithKing(final Board board) {
        Map<Result, Color> gameResult = new HashMap<>();
        if (board.isKingAlive(Color.WHITE)) {
            gameResult.put(Result.WIN, Color.WHITE);
        }
        if (board.isKingAlive(Color.BLACK)) {
            gameResult.put(Result.WIN, Color.BLACK);
        }
        return gameResult;
    }
}
