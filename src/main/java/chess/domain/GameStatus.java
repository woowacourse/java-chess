package chess.domain;

import java.util.Arrays;

public enum GameStatus {

    READY("ready"),
    PLAYING("playing"),
    END("end"),
    KING_DIE("king_die"),
    ;

    private final String value;

    GameStatus(final String value) {
        this.value = value;
    }

    public static GameStatus from(String value) {
        return Arrays.stream(values())
                .filter(it -> it.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("게임 상태를 찾을 수 없습니다."));
    }

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

    public String getValue() {
        return value;
    }
}
