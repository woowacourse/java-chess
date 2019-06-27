package service;

import model.Position;
import model.game.Turn;

public class LogVO implements Comparable<LogVO> {
    final Turn turn;
    final Position from;
    final Position to;

    public LogVO(final Turn turn, final Position from, final Position to) {
        this.turn = turn;
        this.from = from;
        this.to = to;
    }

    public LogVO(final int turnCount, final String from, final String to) {
        turn = new Turn(turnCount);
        this.from = Position.of(from);
        this.to = Position.of(to);
    }

    public Turn turn() {
        return turn;
    }

    public Position from() {
        return this.from;
    }

    public Position to() {
        return this.to;
    }

    @Override
    public int compareTo(final LogVO rhs) {
        return turn.count() - rhs.turn.count();
    }

    @Override
    public String toString() {
        return turn.count() + " : " + this.from.toString() + " -> " + this.to.toString();
    }
}