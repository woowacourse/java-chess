package chess.piece;

public abstract class Piece {
    Type type;
    Color color;

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    abstract boolean isMovable(int x, int y, int toX, int toY);

    public String getType() {
        return type.getSymbol(color);
    }

    public Color getColor() {
        return color;
    }
}
