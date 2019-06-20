package chess.model;

public class Queen {
    private final ChessPieceColor color;
    private final ChessPieceType type;

    public Queen(final ChessPieceColor color) {
        this.color = color;
        this.type = ChessPieceType.QUEEN;
    }


    public boolean canMove(final Point source, final Point target) {
        int xDiff = source.calculateXsDiff(target);
        int yDiff = source.calculateYsDiff(target);
        if ((xDiff == 0 || yDiff == 0) || (xDiff == yDiff)) {
            return true;
        }
        return false;
    }
}
