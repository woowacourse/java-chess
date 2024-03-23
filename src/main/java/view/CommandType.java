package view;

import java.util.Arrays;

public enum CommandType {
    START("start"),
    END("end"),
    MOVE("move");
    private final String command;

    CommandType(final String command) {
        this.command = command;
    }

    public static CommandType from(String command) {
        return Arrays.stream(CommandType.values())
                .filter(element -> element.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 게임 명령어입니다."));
    }
}
