package chess.domain;

public class Queen extends Piece {

    private static final Queen blackQueen;
    private static final Queen whiteQueen;

    static {
        blackQueen = new Queen(Side.BLACK);
        whiteQueen = new Queen(Side.WHITE);
    }


    private Queen(final Side side) {
        super(side);
    }

    public static Queen of(final Side side) {
        if (side == Side.BLACK) {
            return blackQueen;
        }
        return whiteQueen;
    }

    @Override
    boolean isMovable(final Square from, final Square to, final Piece piece) {
        return isNotSameSide(piece) &&
                (isLine(from, to) || isDiagonal(from, to));
    }

    private boolean isDiagonal(final Square from, final Square to) {
        from.validateNotSameSquare(to);
        final int verticalDistance = from.calculateVerticalDistance(to);
        final int horizontalDistance = from.calculateHorizontalDistance(to);
        return verticalDistance == horizontalDistance;
    }

    private boolean isLine(final Square from, final Square to) {
        from.validateNotSameSquare(to);
        final int verticalDistance = from.calculateVerticalDistance(to);
        final int horizontalDistance = from.calculateHorizontalDistance(to);
        return verticalDistance == 0 || horizontalDistance == 0;
    }
}
