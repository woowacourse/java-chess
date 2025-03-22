package chess.piece;

import chess.position.Color;
import chess.position.Position;
import java.util.Set;

public abstract class Piece {

    protected final Color color;
    protected Position position;

    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public boolean isSameColor(Piece other) {
        return this.color == other.color;
    }

    public abstract void move(int x, int y);

    public abstract Set<Position> calculatePath(int x, int y);

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }
}
