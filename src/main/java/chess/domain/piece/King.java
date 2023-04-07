package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.PieceType;

public class King extends Piece {

    private static final King blackKing;
    private static final King whiteKing;

    static {
        blackKing = new King(Color.BLACK);
        whiteKing = new King(Color.WHITE);
    }

    public King(final Color color) {
        super(color, PieceType.KING);
    }

    public static King getKingOf(final Color color) {
        if (color == Color.BLACK) {
            return blackKing;
        }
        return whiteKing;
    }

    @Override
    public boolean isValidMove(final Position from, final Position to, final Piece piece) {
        return this.isNotSameSide(piece)
                && isKingsRange(from, to);
    }

    public boolean isKingsRange(final Position from, final Position to) {
        from.validateNotSameSquare(to);

        final int verticalDistance = from.calculateVerticalDistance(to);
        final int horizontalDistance = from.calculateHorizontalDistance(to);

        return isKingsDistance(verticalDistance, horizontalDistance);
    }

    private boolean isKingsDistance(final int verticalDistance, final int horizontalDistance) {
        return (verticalDistance == 1 || verticalDistance == 0) &&
                (horizontalDistance == 1 || horizontalDistance == 0);
    }
}
