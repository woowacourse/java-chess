package chess.piece;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public abstract class Piece {
    private Type type;
    protected Color color;

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    public abstract boolean isMovable(Pair<Integer, Integer> source, Pair<Integer, Integer> target);

    public abstract List<Pair<Integer, Integer>> computeBetweenTwoPosition(Pair<Integer, Integer> source,
                                                                           Pair<Integer, Integer> target);

    public boolean isSameType(Type type) {
        return this.type == type;
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
