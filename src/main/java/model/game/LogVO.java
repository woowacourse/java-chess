package model.game;

import model.board.Position;

public class LogVO implements Comparable<LogVO> {
    final Turn turn;
    final Position from;
    final Position to;

    public LogVO(Turn turn, Position from, Position to) {
        this.turn = turn;
        this.from = from;
        this.to = to;
    }

    public LogVO(int turnCount, String from, String to) {
        this.turn = new Turn(turnCount);
        this.from = Position.of(from);
        this.to = Position.of(to);
    }

    public Turn turn() {
        return this.turn;
    }

    public Position from() {
        return this.from;
    }

    public Position to() {
        return this.to;
    }

    @Override
    public int compareTo(LogVO rhs) {
        return this.turn.count() - rhs.turn.count();
    }

    @Override
    public String toString() {
        return this.turn.count() + " : " + this.from.toString() + " -> " + this.to.toString();
    }
}