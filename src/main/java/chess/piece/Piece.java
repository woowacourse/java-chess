package chess.piece;

import chess.position.Position;

import java.util.List;

public abstract class Piece {

    private final Type type;
    protected Color color;

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    public abstract boolean isMovable(Position source, Position target);

    public abstract List<Position> computeBetweenTwoPosition(Position source, Position target);

    public boolean isSameType(Type type) {
        return this.type == type;
    }

    public boolean isColor(Color color) {
        return this.color == color;
    }

    public String getSymbolByColor() {
        return type.getSymbol(color);
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }
}
