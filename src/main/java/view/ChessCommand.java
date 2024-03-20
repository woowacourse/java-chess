package view;

import java.util.Arrays;

public enum ChessCommand {
    START("start"),
    END("end"),
    MOVE("move");
    private final String command;

    ChessCommand(final String command) {
        this.command = command;
    }

    public static ChessCommand from(String command) {
        return Arrays.stream(ChessCommand.values())
                .filter(element -> element.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 게임 명령어입니다."));
    }
}
