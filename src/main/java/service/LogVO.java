package service;

import model.board.Position;
import model.game.Turn;

import java.util.Optional;

public class LogVO implements Comparable<LogVO> {
    final Turn turn;
    final Position src;
    final Position dest;

    public LogVO(final Turn turn, final Position src, final Position dest) {
        this.turn = Optional.ofNullable(turn).orElseThrow(IllegalArgumentException::new);
        this.src = Optional.ofNullable(src).orElseThrow(IllegalArgumentException::new);
        this.dest = Optional.ofNullable(dest).orElseThrow(IllegalArgumentException::new);
    }

    public LogVO(final int turnCount, final String src, final String dest) {
        this.turn = new Turn(turnCount);
        this.src = Position.ofSafe(src).orElseThrow(IllegalArgumentException::new);
        this.dest = Position.ofSafe(dest).orElseThrow(IllegalArgumentException::new);
    }

    public Turn turn() {
        return this.turn;
    }

    public Position src() {
        return this.src;
    }

    public Position dest() {
        return this.dest;
    }

    @Override
    public int compareTo(final LogVO rhs) {
        return this.turn.count() - rhs.turn.count();
    }

    @Override
    public String toString() {
        return this.turn.count() + " : " + this.src.toString() + " -> " + this.dest.toString();
    }
}