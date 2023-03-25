package chess.controller;

import java.util.Arrays;

public enum InitCommand {

    NEW("new"),
    CONTINUE("continue");

    private final String name;

    InitCommand(final String name) {
        this.name = name;
    }

    public static InitCommand findByString(final String name) {
        return Arrays.stream(values())
                .filter(command -> command.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 커맨드입니다."));
    }

    public boolean isContinue() {
        return this == CONTINUE;
    }
}
