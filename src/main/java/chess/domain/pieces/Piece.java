package chess.domain.pieces;

import chess.domain.Point;

import java.util.List;

public abstract class Piece {

    private final Type type;
    protected final Color color;

    Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    abstract public List<Point> move(Point source, Point target);

    abstract public List<Point> attack(Point source, Point target);

    public boolean equalsType(Type another) {
        return this.type == another;
    }

    public boolean equalsColor(Color another) {
        return this.color == another;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (type != piece.type) return false;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
