package chess.domain;

public enum GameStatus {

    READY,
    PLAYING,
    END,
    KING_DIE,
    ;

    public void checkReady() {
        if (this.equals(PLAYING)) {
            throw new IllegalArgumentException("게임이 이미 시작되었습니다.");
        }
    }

    public void checkPlaying() {
        if (this.equals(READY)) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
        if (isEnd()) {
            throw new IllegalArgumentException("게임이 이미 종료되었습니다.");
        }
    }

    public boolean isEnd() {
        return this.equals(END) || this.equals(KING_DIE);
    }
}
