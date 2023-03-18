package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;
import chess.domain.Position;

public abstract class Piece {

    private final String name;
    private final Color color;

    public Piece(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String findName() {
        if (color == Color.WHITE) {
            return name.toLowerCase();
        }
        return name.toUpperCase();
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public Direction findDirectionToMove(Position start, Position end) {
        return Direction.findDirectionByGap(start, end);
    }

    abstract public boolean isMovable(Position start, Position end, Color colorOfDestination);
    abstract protected void checkMovableDirection(Direction direction);

    public Color getColor() {
        return color;
    }
}
