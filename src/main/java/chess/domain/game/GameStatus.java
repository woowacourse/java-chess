package chess.domain.game;

public enum GameStatus {

    RUNNING,
    TERMINATED;

    public boolean isTerminated() {
        return this == TERMINATED;
    }
}
