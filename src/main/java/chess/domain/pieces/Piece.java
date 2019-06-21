package chess.domain.pieces;

import chess.domain.Point;
import chess.domain.movepatterns.MovePattern;

import java.util.List;

public abstract class Piece {
    protected final MovePattern movePattern;
    protected final Color color;

    public Piece(Color color, MovePattern movePattern) {
        this.color = color;
        this.movePattern = movePattern;
    }

    public abstract boolean isValidMovePattern(Point source, Point target);
    public abstract List<Point> makePath(Point source, Point target);

    public boolean isSameColor(Color anotherColor) {
        return this.color.equals(anotherColor);
    }
}
