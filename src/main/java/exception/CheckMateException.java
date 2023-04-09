package exception;

import domain.Turn;

import java.util.Objects;

public class CheckMateException extends RuntimeException {
    private final Turn turn;

    public CheckMateException(Turn turn) {
        this.turn = turn;
    }

    public Turn getTurn() {
        return turn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckMateException that = (CheckMateException) o;
        return turn == that.turn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(turn);
    }
}
