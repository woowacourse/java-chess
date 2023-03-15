package chess.controller;

import java.util.Arrays;

public enum GameCommand {
    START("start"),
    END("end");

    private final String value;

    GameCommand(final String value) {
        this.value = value;
    }

    public static GameCommand findBy(String inputValue) {
        return Arrays.stream(GameCommand.values())
                .filter(command -> command.value.equals(inputValue))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어 입니다. 입력 값 :" + inputValue));
    }
}
