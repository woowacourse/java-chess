package chess.controller;

import java.util.Arrays;

public enum Command {
    START("start"),
    MOVE("move"),
    END("end");

    private final String code;

    Command(final String code) {
        this.code = code;
    }

    public static Command from(final String code) {
        return Arrays.stream(Command.values())
                .filter(command -> command.code.equals(code))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어입니다."));
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
