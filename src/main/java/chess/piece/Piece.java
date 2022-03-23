package chess.piece;

import org.apache.commons.lang3.tuple.Pair;

public abstract class Piece {
    Type type;
    Color color;

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    abstract boolean isMovable(Pair<Integer,Integer> source, Pair<Integer,Integer> target);

    public String getType() {
        return type.getSymbol(color);
    }

    public Color getColor() {
        return color;
    }
}
