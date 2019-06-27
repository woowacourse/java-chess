package chess.model.piece;

import chess.model.AbstractBoardNavigator;
import chess.model.Point;

public class Knight extends AbstractChessPiece {
    private static final int MOVABLE_DISTANCE = 5;
    private static final int SQUARE = 2;

    public Knight(final ChessPieceColor color) {
        super(ChessPieceType.KNIGHT, color);
    }

    @Override
    public boolean canMove(final Point source, final Point target, final AbstractBoardNavigator navigator) {
        int xDiff = source.calculateXsDiff(target);
        int yDiff = source.calculateYsDiff(target);

        return (Math.pow(xDiff, SQUARE) + Math.pow(yDiff, SQUARE) == MOVABLE_DISTANCE);
    }

    @Override
    public double getScore(Point point, final AbstractBoardNavigator navigator) {
        return 2.5;
    }
}
