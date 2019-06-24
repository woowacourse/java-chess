package chess.model;

public abstract class AbstractChessPiece {
    private ChessPieceType type;
    private ChessPieceColor color;

    protected AbstractChessPiece(ChessPieceType type, ChessPieceColor color) {
        this.type = type;
        this.color = color;
    }

    public abstract boolean canMove(Point source, Point target, AbstractBoardNavigator navigator);

    public boolean isSameColor(ChessPieceColor color) {
        return this.color.equals(color);
    }

    public boolean isType(ChessPieceType type) {
        return this.type.equals(type);
    }
}
