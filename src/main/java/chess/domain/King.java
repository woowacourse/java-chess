package chess.domain;

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
    boolean isMovable(final Square from, final Square to, final Piece piece) {
        return this.isNotSameSide(piece) && inKingsRange(from, to);
    }

    public boolean inKingsRange(final Square from, final Square to) {
        from.validateNotSameSquare(to);
        final int verticalDistance = from.calculateVerticalDistance(to);
        final int horizontalDistance = from.calculateHorizontalDistance(to);
        return (verticalDistance == 1 || verticalDistance == 0) &&
                (horizontalDistance == 1 || horizontalDistance == 0);
    }
}
