package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.Collections;
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
        return calculatePieceScoreOfColor(color) - calculatePawnDecreaseScore(color);
    }

    private double calculatePieceScoreOfColor(final Color color) {
        return board.keySet().stream()
                .filter(key -> board.get(key).isSameColor(color))
                .map(board::get)
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private double calculatePawnDecreaseScore(final Color color) {
        return board.keySet().stream()
                .filter(key -> board.get(key).isSameColor(color))
                .filter(key -> board.get(key).isPawn())
                .filter(key -> isExistOtherPawnInFile(key, color))
                .map(board::get)
                .mapToDouble(piece -> piece.getScore() * PAWN_SCORE_DECREASE_RATE)
                .sum();
    }

    private boolean isExistOtherPawnInFile(final Square square, final Color color) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank != square.getRank())
                .filter(rank -> board.containsKey(new Square(square.getFile(), rank)))
                .map(rank -> board.get(new Square(square.getFile(), rank)))
                .filter(piece -> piece.isSameColor(color))
                .anyMatch(Piece::isPawn);
    }

    public BoardSnapShot getBoardSnapShot() {
        return BoardSnapShot.from(board);
    }

    public Map<Square, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
