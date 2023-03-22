package chess.controller;

import java.util.Objects;

public enum GameStatus {
    PLAYING("move"),
    END("end");

    private final String code;

    GameStatus(final String code) {
        this.code = code;
    }

    public static GameStatus startGame() {
        return PLAYING;
    }

    public static GameStatus changeStatus(String code) {
        if (Objects.equals(PLAYING.code, code)) {
            return PLAYING;
        }
        if (Objects.equals(END.code, code)) {
            return END;
        }
        throw new IllegalArgumentException("잘못된 명령어입니다.");
    }

    public boolean isPlaying() {
        return this != GameStatus.END;
    }
}
