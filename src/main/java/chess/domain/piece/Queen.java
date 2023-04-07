package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.PieceType;

public class Queen extends Piece {

    private static final Queen blackQueen;
    private static final Queen whiteQueen;

    static {
        blackQueen = new Queen(Color.BLACK);
        whiteQueen = new Queen(Color.WHITE);
    }

    public Queen(final Color color) {
        super(color, PieceType.QUEEN);
    }

    public static Queen getQueenOf(final Color color) {
        if (color == Color.BLACK) {
            return blackQueen;
        }
        return whiteQueen;
    }

    @Override
    public boolean isValidMove(final Position from, final Position to, final Piece piece) {
        return isNotSameSide(piece) &&
                (isLine(from, to) || isDiagonal(from, to));
    }

    private boolean isDiagonal(final Position from, final Position to) {
        from.validateNotSameSquare(to);

        final int verticalDistance = from.calculateVerticalDistance(to);
        final int horizontalDistance = from.calculateHorizontalDistance(to);

        return isDiagonal(verticalDistance, horizontalDistance);
    }

    private boolean isDiagonal(final int verticalDistance, final int horizontalDistance) {
        return verticalDistance == horizontalDistance;
    }

    private boolean isLine(final Position from, final Position to) {
        from.validateNotSameSquare(to);

        final int verticalDistance = from.calculateVerticalDistance(to);
        final int horizontalDistance = from.calculateHorizontalDistance(to);

        return isLine(verticalDistance, horizontalDistance);
    }

    private boolean isLine(final int verticalDistance, final int horizontalDistance) {
        return verticalDistance == 0 || horizontalDistance == 0;
    }
}
