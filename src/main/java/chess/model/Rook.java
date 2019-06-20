package chess.model;

public class Rook {
    private final ChessPieceType type;
    private final ChessPieceColor color;

    public Rook(final ChessPieceColor color) {
        this.type = ChessPieceType.ROOK;
        this.color = color;
    }

    public boolean canMove(final Point source, final Point target) {
        return (source.calculateXsDiff(target) == 0 || source.calculateYsDiff(target) == 0);
    }
}
