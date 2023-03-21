package chess.controller;

import java.util.Objects;

public enum Command {
    START("start"),
    MOVE("move"),
    END("end");

    private final String code;

    Command(final String code) {
        this.code = code;
    }

    public static Command from(String code) {
        if (Objects.equals(code, START.code)) {
            return START;
        }
        if (Objects.equals(code, MOVE.code)) {
            return MOVE;
        }
        if (Objects.equals(code, END.code)) {
            return END;
        }
        throw new IllegalArgumentException("잘못된 명령어입니다.");
    }

    public void validateCommandInStart() {
        if (this == START) {
            return;
        }
        throw new IllegalArgumentException("게임을 시작하기 위해서는 start를 입력하세요");
    }

    public void validateCommandInPlaying() {
        if (this == MOVE || this == END) {
            return;
        }
        if (this == START) {
            throw new IllegalArgumentException("게임 도중에는 다시 시작할 수 없습니다.");
        }
    }

    public boolean isPlaying() {
        return this != Command.END;
    }
}
