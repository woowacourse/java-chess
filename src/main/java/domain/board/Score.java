package domain.board;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.Type;
import domain.position.File;
import domain.position.Position;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Score {
    private final double whiteScore;
    private final double blackScore;

    private Score(final double whiteScore, final double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public static Score calculate(final Map<Position, Piece> board) {
        double whiteScore = calculateBoardScore(board, Color.WHITE);
        double blackScore = calculateBoardScore(board, Color.BLACK);
        return new Score(whiteScore, blackScore);
    }

    public static double calculateBoardScore(final Map<Position, Piece> board, final Color color) {
        if (hasNoKingPiece(board, color)) {
            return 0;
        }
        return calculateTotalScore(board, color) - calculateDistinctFilesPenalty(board, color);
    }

    private static boolean hasNoKingPiece(final Map<Position, Piece> board, final Color color) {
        return getPiecesOfColor(board, color)
                .noneMatch(positionPieceEntry -> positionPieceEntry.getValue().type() == Type.KING);
    }

    private static double calculateTotalScore(final Map<Position, Piece> board, final Color color) {
        return getPiecesOfColor(board, color)
                .mapToDouble(positionPieceEntry -> positionPieceEntry.getValue().type().score())
                .sum();
    }

    private static double calculateDistinctFilesPenalty(final Map<Position, Piece> board, final Color color) {
        final Map<File, Long> fileCounts = countPawnPiecesByFile(board, color);

        final long totalDuplicateFiles = fileCounts.values().stream()
                .filter(count -> count > 1)
                .reduce(0L, Long::sum);

        return totalDuplicateFiles * 0.5;
    }

    private static Map<File, Long> countPawnPiecesByFile(final Map<Position, Piece> board, final Color color) {
        return getPiecesOfColor(board, color)
                .filter(entry -> entry.getValue().type() == Type.PAWN)
                .collect(Collectors.groupingBy(
                        entry -> entry.getKey().file(),
                        Collectors.counting()
                ));
    }

    private static Stream<Map.Entry<Position, Piece>> getPiecesOfColor(final Map<Position, Piece> board, final Color color) {
        return board.entrySet().stream()
                .filter(positionPieceEntry -> positionPieceEntry.getValue().color() == color);
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
