package chess.domain;

public enum GameStatus {

    READY,
    PLAYING,
    END,
    KING_DIE,
    ;

    public boolean isReady() {
        return this.equals(READY);
    }

    public boolean isPlaying() {
        return this.equals(PLAYING);
    }

    public boolean isEnd() {
        return this.equals(END) || this.equals(KING_DIE);
    }

    public boolean isKingDie() {
        return this.equals(KING_DIE);
    }
}
