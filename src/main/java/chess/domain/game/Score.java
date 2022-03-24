package chess.domain.game;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

public class Score {

    private final double whiteScore;
    private final double blackScore;
    private final Color winColor;

    public Score(final Map<Position, Piece> board) {
        this.whiteScore = calculateScore(board, Color.WHITE);
        this.blackScore = calculateScore(board, Color.BLACK);
        if (whiteScore > blackScore) {
            this.winColor = Color.WHITE;
            return;
        }
        this.winColor = Color.BLACK;
    }

    public double calculateScore(final Map<Position, Piece> board, final Color color) {
        return calculateFirstLinePieces(board, color) + calculatePawn(board, color);
    }

    private double calculateFirstLinePieces(final Map<Position, Piece> board, final Color color) {
        return board.values().stream()
                .filter(piece -> piece.getColor() == color)
                .filter(piece -> !piece.isPawn())
                .mapToDouble(Piece::getPoint)
                .sum();
    }

    private double calculatePawn(final Map<Position, Piece> board, final Color color) {
        Map<Column, Integer> pawnCount = new EnumMap<>(Column.class);
        for (final Entry<Position, Piece> boardEntry : board.entrySet()) {
            putPawnCount(color, pawnCount, boardEntry);
        }

        return pawnCount.values().stream()
                .mapToDouble(this::adjustPawnPoint)
                .sum();
    }

    private void putPawnCount(final Color color, final Map<Column, Integer> pawnCount,
                              final Entry<Position, Piece> boardEntry) {
        Piece piece = boardEntry.getValue();
        if (piece.isPawn() && piece.getColor() == color) {
            Column column = boardEntry.getKey().getColumn();
            pawnCount.put(column, pawnCount.getOrDefault(column, 0) + 1);
        }
    }

    private double adjustPawnPoint(final int count) {
        if (count > 1) {
            return count * 0.5;
        }
        return count;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public Color getWinColor() {
        return winColor;
    }
}
