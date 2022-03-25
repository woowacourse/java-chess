package chess.position;

import java.util.Collection;

public class Transition {

    private final Position from;
    private final Position to;

    public Transition(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public Position getTo() {
        return to;
    }

    public Position getFrom() {
        return from;
    }

    public boolean isVerticalWay() {
        return from.isVerticalWay(to);
    }

    public boolean isHorizontalWay() {
        return from.isHorizontalWay(to);
    }

    public boolean isDiagonalWay() {
        return from.isDiagonalWay(to);
    }

    public boolean isAdjacent() {
        return from.isAdjacent(to);
    }

    public Collection<Position> getPath() {
        return from.getPath(to);
    }

    public int getHorizontalDistance() {
        return from.getHorizontalDistance(to);
    }

    public int getVerticalDistance() {
        return from.getVerticalDistance(to);
    }

    public boolean isUpward() {
        return from.isUpward(to);
    }

    public boolean isDownward() {
        return from.isDownward(to);
    }
}
