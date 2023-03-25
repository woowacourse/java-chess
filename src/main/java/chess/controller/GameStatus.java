package chess.controller;

import java.util.Arrays;
import java.util.Objects;

public enum GameStatus {
    PLAYING("move"),
    END("end");

    private final String code;

    GameStatus(final String code) {
        this.code = code;
    }

    public static GameStatus getInitialGameStatus() {
        return PLAYING;
    }

    public static GameStatus getNextStatus(String code) {
        validateCodeExistence(code);

        if (Objects.equals(PLAYING.code, code)) {
            return PLAYING;
        }
        return END;
    }

    private static void validateCodeExistence(final String code) {
        if (isCodeNotExist(code)) {
            throw new IllegalArgumentException("잘못된 명령어입니다.");
        }
    }

    private static boolean isCodeNotExist(final String code) {
        return Arrays.stream(values())
                     .noneMatch((gameStatus) -> Objects.equals(code, gameStatus.code));
    }

    public boolean isPlaying() {
        return this != GameStatus.END;
    }
}

