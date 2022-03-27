package chess.domain.game;

public enum GameStatus {
    READY,
    PLAYING,
    END,
    ;

    public boolean isReady() {
        return this == READY;
    }

    public boolean isPlaying() {
        return this.equals(PLAYING);
    }

    public boolean isEnd() {
        return this.equals(END);
    }
}
