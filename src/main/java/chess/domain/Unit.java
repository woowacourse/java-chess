package chess.domain;

import chess.domain.movepatterns.MovePattern;

import java.util.List;

public abstract class Unit {
    protected final MovePattern movePattern;
    protected final Integer color;

    public Unit(Integer color, MovePattern movePattern) {
        this.color = color;
        this.movePattern = movePattern;
    }

    abstract boolean isValidMovePattern(Point source, Point target);
    abstract List<Point> makePath(Point source, Point target);

    public boolean isSameColor(Integer anotherColor) {
        return this.color.equals(anotherColor);
    }
}
