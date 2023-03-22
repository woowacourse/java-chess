package domain;

import domain.piece.Piece;
import domain.type.Color;
import domain.type.PieceType;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class ScoreCalculator {

    private static final double PAWN_SUB_SCORE = 0.5;

    public double calculateWhite(final Map<Location, Piece> board) {
        return board.keySet()
            .stream()
            .mapToDouble(location -> getScore(board, location, Color.WHITE))
            .sum();
    }

    public double calculateBlack(final Map<Location, Piece> board) {
        return board.keySet()
            .stream()
            .mapToDouble(location -> getScore(board, location, Color.BLACK))
            .sum();
    }

    private double getScore(final Map<Location, Piece> board, final Location location, final Color color) {
        final Piece piece = board.get(location);
        if (piece.isDifferentColor(color)) {
            return 0D;
        }
        if (piece.isSameType(PieceType.PAWN)) {
            return getPawnScore(board, location);
        }
        return piece.getScore();
    }

    private double getPawnScore(final Map<Location, Piece> board, final Location location) {
        final Piece piece = board.get(location);
        if (checkPawnInSameColumn(board, location, piece.getColor())) {
            return PAWN_SUB_SCORE;
        }
        return piece.getScore();
    }

    private boolean checkPawnInSameColumn(final Map<Location, Piece> board, final Location location,
        final Color color) {
        return IntStream.rangeClosed(1, 8)
            .filter(row -> row != location.getRow())
            .mapToObj(row -> board.get(Location.of(location.getColumn(), row)))
            .filter(Objects::nonNull)
            .anyMatch(piece ->
                piece.isSameColor(color) && piece.isSameType(PieceType.PAWN)
            );
    }
}
