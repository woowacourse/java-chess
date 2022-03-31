package chess.domain.board;

import chess.domain.piece.Piece;
import java.util.List;

public class Directions {
    public static final List<Integer> PAWN_ANGLES = List.of(90, 135, 45);
    public static final List<Integer> KNIGHT_ANGLES = List.of(63, 26, -26, -63, -116, -153, 153, 116);
    public static final List<Integer> VERTICAL_OR_HORIZONTAL_ANGLES = List.of(90, 0, -90, 180);
    public static final List<Integer> DIAGONAL_ANGLES = List.of(45, -45, -135, 135);

    private Directions() {
    }

    public static boolean isMovableDirection(Piece piece, int dx, int dy) {
        final int angle = calculateAngleOfTwoPositions(dx, dy);

        if (piece.isPawn()) {
            return PAWN_ANGLES.contains(angle);
        }

        if (piece.isQueen() || piece.isKing()) {
            return VERTICAL_OR_HORIZONTAL_ANGLES.contains(angle) || DIAGONAL_ANGLES.contains(angle);
        }

        if (piece.isRook()) {
            return VERTICAL_OR_HORIZONTAL_ANGLES.contains(angle);
        }

        if (piece.isBishop()) {
            return DIAGONAL_ANGLES.contains(angle);
        }

        if (piece.isKnight()) {
            return KNIGHT_ANGLES.contains(angle);
        }

        return false;
    }

    public static int calculateAngleOfTwoPositions(int dx, int dy) {
        return (int) (Math.atan2(dy, dx) * (180.0 / Math.PI));
    }

    public static boolean isMovableDirection(Piece piece, Position from, Position to) {
        final int angle = calculateAngleOfTwoPositions(from, to);

        if (piece.isPawn()) {
            return PAWN_ANGLES.contains(angle);
        }

        if (piece.isQueen() || piece.isKing()) {
            return VERTICAL_OR_HORIZONTAL_ANGLES.contains(angle) || DIAGONAL_ANGLES.contains(angle);
        }

        if (piece.isRook()) {
            return VERTICAL_OR_HORIZONTAL_ANGLES.contains(angle);
        }

        if (piece.isBishop()) {
            return DIAGONAL_ANGLES.contains(angle);
        }

        if (piece.isKnight()) {
            return KNIGHT_ANGLES.contains(angle);
        }

        return false;
    }

    public static int calculateAngleOfTwoPositions(Position from, Position to) {
        final List<Integer> differential = from.calculateDistance(to);
        return (int) (Math.atan2(differential.get(1), differential.get(0)) * (180.0 / Math.PI));
    }
}
