package chess.piece;

import chess.chessboard.Position;
import chess.chessboard.Side;

public class Queen extends Piece {

    private static final Queen blackQueen;
    private static final Queen whiteQueen;

    static {
        blackQueen = new Queen(Side.BLACK);
        whiteQueen = new Queen(Side.WHITE);
    }

    public Queen(final Side side) {
        super(side, PieceType.QUEEN);
    }

    public static Queen getQueenOf(final Side side) {
        if (side == Side.BLACK) {
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
