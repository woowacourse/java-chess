package chess.domain;

public enum GameStatus {

    READY,
    PLAYING,
    END;

    public boolean isReady() {
        return this.equals(READY);
    }

    public boolean isPlaying() {
        return this.equals(PLAYING);
    }

    public boolean isEnd() {
        return this.equals(END);
    }
}
