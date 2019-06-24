package chess.model;

public class Rook extends AbstractRangeChessPiece {
    public Rook(final ChessPieceColor color) {
        super(ChessPieceType.ROOK, color);
    }

    public boolean canMove(final Point source, final Point target, AbstractBoardNavigator navigator) {
        if (isObstacleBetween(source, target, navigator)) return false;
        return (source.calculateXsDiff(target) == 0 || source.calculateYsDiff(target) == 0);
    }
}
