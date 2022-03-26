package chess.piece;

import chess.chessgame.Position;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public abstract class Piece {
    private Type type;
    protected Color color;

    public Piece(Type type, Color color) {
        this.type = type;
        this.color = color;
    }

    public abstract boolean isMovable(Position position);

    public abstract List<Pair<Integer, Integer>> computeMiddlePosition(Position position);

    public boolean isSameType(Type type) {
        return this.type == type;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public String getSymbolByColor() {
        return type.getSymbol(color);
    }

    public double getScore() {
        return type.getScore();
    }

    public Color getColor(){
        return color;
    }
}
