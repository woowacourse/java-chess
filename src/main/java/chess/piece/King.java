package chess.piece;

import chess.chessboard.Side;
import chess.chessboard.Square;

public class King extends Piece {

    private static final King blackKing;
    private static final King whiteKing;

    static {
        blackKing = new King(Side.BLACK);
        whiteKing = new King(Side.WHITE);
    }

    private King(final Side side) {
        super(side);
    }

    public static King getKingOf(final Side side) {
        if (side == Side.BLACK) {
            return blackKing;
        }
        return whiteKing;
    }

    @Override
    public boolean isMovable(final Square source, final Square destination, final Piece piece) {
        return this.isNotSameSide(piece) && isKingsRange(source, destination);
    }

    public boolean isKingsRange(final Square source, final Square destination) {
        source.validateNotSameSquare(destination);

        final int verticalDistance = source.calculateVerticalDistance(destination);
        final int horizontalDistance = source.calculateHorizontalDistance(destination);

        return isKingsDistance(verticalDistance, horizontalDistance);
    }

    private boolean isKingsDistance(final int verticalDistance, final int horizontalDistance) {
        return (verticalDistance == 1 || verticalDistance == 0) &&
                (horizontalDistance == 1 || horizontalDistance == 0);
    }
}
