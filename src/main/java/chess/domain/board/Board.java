package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Board {

    private static final double PAWN_SCORE_DECREASE_RATE = 0.5;

    private final Map<Square, Piece> board;

    public Board(final Map<Square, Piece> board) {
        this.board = board;
    }

    public Optional<Piece> findPieceOf(final Square square) {
        return Optional.ofNullable(board.get(square));
    }

    public void move(final Square source, final Square destination) {
        board.put(destination, board.remove(source));
    }

    public double calculateScoreOfColor(final Color color) {
        return calculateScoreOfColorExceptPawn(color) + calculateScoreOfColorForPawn(color);
    }

    public double calculateScoreOfColorExceptPawn(final Color color) {
        return board.keySet().stream()
                .filter(key -> board.get(key).isSameColor(color))
                .filter(key -> !board.get(key).isPawn())
                .map(board::get)
                .mapToDouble(Piece::getScore)
                .sum();
    }

    public double calculateScoreOfColorForPawn(final Color color) {
        final Map<File, Integer> pawnCountTable = createPawnCountTableOfColor(color);
        return board.keySet().stream()
                .filter(key -> board.get(key).isSameColor(color))
                .filter(key -> board.get(key).isPawn())
                .mapToDouble(key -> calculatePawnScore(key, pawnCountTable))
                .sum();
    }

    private Map<File, Integer> createPawnCountTableOfColor(final Color color) {
        final Map<File, Integer> table = new HashMap<>();
        for (final Square square : board.keySet()) {
            updateTableIfPawn(table, square, color);
        }
        return table;
    }

    private void updateTableIfPawn(final Map<File, Integer> table, final Square square, final Color color) {
        final Piece piece = board.get(square);
        if (piece.isPawn() && piece.isSameColor(color)) {
            final File file = square.getFile();
            table.put(file, table.getOrDefault(file, 0) + 1);
        }
    }

    private double calculatePawnScore(final Square square, final Map<File, Integer> table) {
        final Piece piece = board.get(square);
        if (piece.isPawn() && table.get(square.getFile()) > 1) {
            return piece.getScore() * PAWN_SCORE_DECREASE_RATE;
        }
        return piece.getScore();
    }

    public BoardSnapShot getBoardSnapShot() {
        return BoardSnapShot.from(board);
    }

    public Map<Square, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
