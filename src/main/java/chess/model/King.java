package chess.model;

public class King {
    private static final int STEP_SIZE = 1;
    private ChessPieceColor color;
    private ChessPieceType type;

    public King(final ChessPieceColor color) {
        this.type = ChessPieceType.KING;
        this.color = color;
    }

    public boolean canMove(final Point source, final Point target) {
        int xDiff = source.calculateXsDiff(target);
        int yDiff = source.calculateYsDiff(target);

        if (xDiff <= STEP_SIZE && yDiff <= STEP_SIZE) {
            return true;
        }

        return false;
    }
}
