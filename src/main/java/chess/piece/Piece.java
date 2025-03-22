package chess.piece;

import chess.Color;
import chess.Position;

public abstract class Piece {

    protected final Color color;
    protected Position position;

    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public boolean isSameTeam(Color color) {
        return this.color.compareTo(color) == 0;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isSamePosition(Position position) {
        return this.position.equals(position);
    }

    public abstract void move(Position end);

    public abstract String name();
}
