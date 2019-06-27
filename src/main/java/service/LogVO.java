package service;

import model.Position;
import model.game.Turn;

public class LogVO implements Comparable<LogVO> {
    final Turn isTurnOf;
    final Position from;
    final Position to;

    public LogVO(final Turn isTurnOf, final Position from, final Position to) {
        this.isTurnOf = isTurnOf;
        this.from = from;
        this.to = to;
    }

    public LogVO(final int turnCount, final String from, final String to) {
        this.isTurnOf = new Turn(turnCount);
        this.from = Position.of(from);
        this.to = Position.of(to);
    }

    public Turn isTurnOf() {
        return this.isTurnOf;
    }

    public Position from() {
        return this.from;
    }

    public Position to() {
        return this.to;
    }

    @Override
    public int compareTo(final LogVO rhs) {
        return this.isTurnOf.count() - rhs.isTurnOf.count();
    }

    @Override
    public String toString() {
        return this.isTurnOf.count() + " : " + this.from.toString() + " -> " + this.to.toString();
    }
}