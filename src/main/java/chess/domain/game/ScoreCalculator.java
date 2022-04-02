package chess.domain.game;

import java.util.Map;

import chess.domain.Position;
import chess.domain.piece.Piece;

public class ScoreCalculator {

    private static final double PAWN_DISCOUNT_SCORE = 0.5;

    public double calculateScore(final Map<Position, Piece> pieces) {
        return calculateColorDefaultScore(pieces) - PAWN_DISCOUNT_SCORE * countDuplicatedColumnPawn(pieces);
    }

    private double calculateColorDefaultScore(final Map<Position, Piece> pieces) {
        return pieces.values()
                .stream()
                .mapToDouble(Piece::getPieceScore)
                .sum();
    }

    private int countDuplicatedColumnPawn(final Map<Position, Piece> pieces) {
        return (int) pieces.entrySet()
                .stream()
                .filter(entry -> isPawn(entry.getValue()))
                .filter(entry -> existOtherPawnInSameColumn(pieces, entry.getKey()))
                .count();
    }

    private boolean existOtherPawnInSameColumn(final Map<Position, Piece> pieces, final Position originPosition) {
        return pieces.entrySet()
                .stream()
                .filter(entry -> isPawn(entry.getValue()))
                .anyMatch(entry -> isSameColumn(entry.getKey(), originPosition));
    }

    private boolean isPawn(final Piece piece) {
        return piece.isPawn();
    }

    private boolean isSameColumn(final Position position1, final Position position2) {
        return !position1.equals(position2) && position1.equalsColumn(position2);
    }
}
