package chess.controller;

import java.util.Arrays;

public enum CommandTest {
    START("start", new StartAction()),
    END("end", new StartAction()),
    MOVE("move", new StartAction());

    private final String command;
    private final WugaAction action;

    CommandTest(String command, WugaAction action) {
        this.command = command;
        this.action = action;
    }

    void execute() {
        action.execute();
    }

    static CommandTest findCommand(String command) {
        return Arrays.stream(CommandTest.values())
                .filter(commands -> commands.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("커맨드 일치 x"));
    }
}
