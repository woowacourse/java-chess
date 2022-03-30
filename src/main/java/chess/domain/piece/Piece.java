package chess.domain.piece;

import chess.domain.position.Position;

import java.util.Map;

public abstract class Piece {

    private final Type type;
    protected Color color;

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    public abstract boolean isMovableDot(Position source, Position target);

    public abstract boolean isMovableLine(Position source, Position target, Map<Position, Piece> board);

    public boolean isSameType(Type type) {
        return this.type == type;
    }

    public boolean isColor(Color color) {
        return this.color == color;
    }

    public boolean isColor(Piece piece) {
        return this.color == piece.color;
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
