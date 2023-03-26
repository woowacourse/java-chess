package chess.domain.piece;

import chess.direction.Direction;
import chess.domain.Position;

import java.util.ArrayList;
import java.util.List;

import static chess.direction.Direction.BOTTOM_LEFT;
import static chess.direction.Direction.BOTTOM_RIGHT;
import static chess.direction.Direction.TOP_LEFT;
import static chess.direction.Direction.TOP_RIGHT;

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

    public Direction findDirection(Position start, Position end) {
        return Direction.findDirectionByGap(start, end, this);
    }

   public abstract boolean isMovable(Position start, Position end, Color colorOfDestination);

    public abstract int calculateKing(int count);

    public abstract int calculatePawn(int count, Color color);

    private static boolean isEqualTo(Direction o1, Direction o2) {
        return (o1.getX() == o2.getX()) && (o1.getY() == o2.getY());
    }

    public static boolean isDiagonal(Direction direction) {
        return isEqualTo(direction, TOP_LEFT)
                || isEqualTo(direction, TOP_RIGHT)
                || isEqualTo(direction, BOTTOM_LEFT)
                || isEqualTo(direction, BOTTOM_RIGHT);
    }

    public abstract boolean findDirection(Direction direction, Position start, Position end, Piece piece);

    public boolean isSameColor(Color colorOfDestination) {
        return this.color.equals(colorOfDestination);
    }

    public Color getColor() {
        return color;
    }

    public double getScore() {
        return score;
    }
}
