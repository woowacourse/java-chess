package chess.domain;

public enum GameStatus {

    READY,
    PLAYING,
    END,
    CHECK_MATE,
    ;

    public boolean isReady() {
        return this.equals(READY);
    }

    public boolean isEnd() {
        return this.equals(END) || this.equals(CHECK_MATE);
    }
}
