package chess.piece;

import chess.chessboard.Side;
import chess.chessboard.Square;

public class Queen extends Piece {

    private static final Queen blackQueen;
    private static final Queen whiteQueen;

    static {
        blackQueen = new Queen(Side.BLACK);
        whiteQueen = new Queen(Side.WHITE);
    }

    private Queen(final Side side) {
        super(side, PieceType.QUEEN);
    }

    public static Queen getQueenOf(final Side side) {
        if (side == Side.BLACK) {
            return blackQueen;
        }
        return whiteQueen;
    }

    @Override
    public boolean isMovable(final Square source, final Square destination, final Piece piece) {
        return isNotSameSide(piece) &&
                (isLine(source, destination) || isDiagonal(source, destination));
    }

    private boolean isDiagonal(final Square source, final Square destination) {
        source.validateNotSameSquare(destination);

        final int verticalDistance = source.calculateVerticalDistance(destination);
        final int horizontalDistance = source.calculateHorizontalDistance(destination);

        return isDiagonal(verticalDistance, horizontalDistance);
    }

    private boolean isDiagonal(final int verticalDistance, final int horizontalDistance) {
        return verticalDistance == horizontalDistance;
    }

    private boolean isLine(final Square source, final Square destination) {
        source.validateNotSameSquare(destination);

        final int verticalDistance = source.calculateVerticalDistance(destination);
        final int horizontalDistance = source.calculateHorizontalDistance(destination);

        return isLine(verticalDistance, horizontalDistance);
    }

    private boolean isLine(final int verticalDistance, final int horizontalDistance) {
        return verticalDistance == 0 || horizontalDistance == 0;
    }
}
