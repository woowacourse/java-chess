package chess.domain;

public enum GameStatus {

    READY,
    PLAYING,
    END;

    public boolean isReady() {
        return this == READY;
    }

    public boolean isPlaying() {
        return this == PLAYING;
    }

    public boolean isEnd() {
        return this == END;
    }
}
