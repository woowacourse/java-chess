package chess.model;

public class King extends AbstractChessPiece{
    private static final int STEP_SIZE = 1;

    public King(final ChessPieceColor color) {
        super(ChessPieceType.KING, color);
    }

    @Override
    public boolean canMove(final Point source, final Point target, final AbstractBoardNavigator navigator) {
        int xDiff = source.calculateXsDiff(target);
        int yDiff = source.calculateYsDiff(target);

        return (xDiff <= STEP_SIZE && yDiff <= STEP_SIZE);
    }
}
