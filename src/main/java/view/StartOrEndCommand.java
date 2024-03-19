package view;

import java.util.Arrays;

public enum StartOrEndCommand {
    START("start"),
    END("end");
    private final String command;

    StartOrEndCommand(final String command) {
        this.command = command;
    }

    public static StartOrEndCommand from(String command) {
        return Arrays.stream(StartOrEndCommand.values())
                .filter(element -> element.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 게임 시작 명령입니다."));
    }
}
