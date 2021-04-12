package chess.domain.chess;

public enum Status {
    STOP, RUNNING, KING_DEAD, TERMINATED;

    public boolean isRunning() {
        return this == RUNNING;
    }

    public boolean isTerminated() {
        return this == TERMINATED;
    }

    public boolean isKingDead() {
        return this == KING_DEAD;
    }

    public boolean isStop() {
        return this == STOP;
    }
}
