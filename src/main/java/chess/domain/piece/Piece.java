package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import chess.practiceMove.Direction;

public abstract class Piece {

    private final String name;
    private final Color color;
    private double score;

    public Piece(String name, Color color, double score) {
        this.name = name;
        this.color = color;
        this.score = score;
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

    public Direction findDirection(Position start, Position end) {
        return Direction.findDirectionByGap(start, end, this);
    }

    abstract public boolean isMovable(Position start, Position end, Color colorOfDestination);

    public Color getColor() {
        return color;
    }

    public double getScore() {
        return score;
    }
}
