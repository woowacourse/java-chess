package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.notation.Color;
import chess.domain.piece.notation.PieceNotation;
import chess.domain.position.Position;

import java.util.Map;

public final class BoardCalculator {

    private static final int MIN_PAWN_COUNT = 1;
    private static final int NUMBER_DIVIDE_PAWN_SCORE = 2;

    private final Map<Position, Piece> board;

    public BoardCalculator(final Map<Position, Piece> board) {
        this.board = board;
    }

    public Map<Color, Double> sumScore(final Color targetColor) {
        final double sum = board.entrySet().stream()
                .filter(e -> e.getValue().isSameColor(targetColor))
                .mapToDouble(this::getScore)
                .sum();
        return Map.of(targetColor, sum);
    }

    private double getScore(final Map.Entry<Position, Piece> entry) {
        final Piece piece = entry.getValue();

        if (piece.isPawn()) {
            return calculatePawnScore(entry);
        }
        return piece.getScore();
    }

    private double calculatePawnScore(final Map.Entry<Position, Piece> entry) {
        final Position position = entry.getKey();
        final Piece pawn = entry.getValue();

        long pawnCountInSameFile = board.entrySet().stream()
                .filter(e -> e.getValue().equals(pawn))
                .filter(e -> e.getKey().getFile() == position.getFile())
                .count();

        if (pawnCountInSameFile > MIN_PAWN_COUNT) {
            return pawn.getScore() / NUMBER_DIVIDE_PAWN_SCORE;
        }
        return pawn.getScore();
    }
}
