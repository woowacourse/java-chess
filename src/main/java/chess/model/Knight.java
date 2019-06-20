package chess.model;

public class Knight {
    private static final int MOVABLE_DISTANCE = 5;
    private static final int SQUARE = 2;
    private ChessPieceColor color;
    private ChessPieceType type;

    public Knight(final ChessPieceColor color) {
        this.color = color;
        this.type = ChessPieceType.KNIGHT;
    }

    public boolean canMove(final Point source, final Point target) {
        int xDiff = source.calculateXsDiff(target);
        int yDiff = source.calculateYsDiff(target);

        return (Math.pow(xDiff, SQUARE) + Math.pow(yDiff, SQUARE) == MOVABLE_DISTANCE);
    }
}
