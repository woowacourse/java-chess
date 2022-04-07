package chess.domain;

import java.util.Arrays;

public enum GameStatus {

    READY,
    PLAYING,
    END,
    CHECK_MATE,
    ;

    public static GameStatus of(String value) {
        return Arrays.stream(values())
                .filter(status -> status.toString().equals(value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 상태가 없습니다."));
    }

    public boolean isReady() {
        return this.equals(READY);
    }

    public boolean isEnd() {
        return this.equals(END) || this.equals(CHECK_MATE);
    }
}
