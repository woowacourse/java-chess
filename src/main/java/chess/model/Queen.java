package chess.model;

public class Queen extends AbstractRangeChessPiece {

    public Queen(final ChessPieceColor color) {
        super(ChessPieceType.QUEEN, color);
    }

    @Override
    public boolean canMove(final Point source, final Point target, final AbstractBoardNavigator navigator) {
        if (isObstacleBetween(source, target, navigator)) return false;
        int xDiff = source.calculateXsDiff(target);
        int yDiff = source.calculateYsDiff(target);

        return ((xDiff == 0 || yDiff == 0) || (xDiff == yDiff));
    }

    @Override
    public double getScore(Point point, final AbstractBoardNavigator navigator) {
        return 9;
    }
}
