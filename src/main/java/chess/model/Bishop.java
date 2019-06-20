package chess.model;

public class Bishop {
    private final ChessPieceType type;
    private final ChessPieceColor color;
    
    public Bishop(final ChessPieceColor color) {
        this.color = color;
        this.type = ChessPieceType.BISHOP;
    }

    public static boolean canMove(final Point source, final Point target) {
        if (source.calculateXsDiff(target) == source.calculateYsDiff(target)) {
            return true;
        }

        return false;
    }
}
