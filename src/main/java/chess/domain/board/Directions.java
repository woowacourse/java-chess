package chess.domain.board;

import chess.domain.piece.Piece;
import java.util.List;

public class Directions {
    public static final List<Double> PAWN_DIRECTIONS = List.of(90.0, -45.0, 45.0);

    private Directions() {
    }

    public static boolean isMovableDirection(Piece piece, Position from, Position to) {
        if (piece.isPawn()) {
            final List<Integer> differential = from.calculateDistance(to);
            double angle = Math.atan2(differential.get(1), differential.get(0)) * (180.0 / Math.PI);
            return PAWN_DIRECTIONS.contains(angle);
        }
        return true;
    }

    public static double calculateAngleOfTwoPositions(Position from, Position to) {
        final List<Integer> differential = from.calculateDistance(to);
        return Math.atan2(differential.get(1), differential.get(0)) * (180.0 / Math.PI);
    }
}
