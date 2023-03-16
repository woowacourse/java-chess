package chess.controller;

import chess.controller.status.CommandType;

import java.util.Arrays;
import java.util.List;

public class Command {
    private final CommandType type;
    private final List<String> commands;

    public Command(final CommandType type, final List<String> commands) {
        this.type = type;
        this.commands = commands;
    }

    private static final String COMMAND_ERROR_MESSAGE = "잘못된 명령어 입력입니다.";

    public static Command findCommand(final List<String> commands) {
        final CommandType type = Arrays.stream(CommandType.values())
                .filter(e -> e.name().equalsIgnoreCase(commands.get(0)))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(COMMAND_ERROR_MESSAGE));
        return new Command(type, commands);
    }

    public boolean isStart() {
        return type == CommandType.START;
    }

    public boolean isEnd() {
        return type == CommandType.END;
    }

    public boolean isCorrectWhenMove() {
        return commands.size() == 3;
    }

    public List<String> getCommands() {
        return List.copyOf(commands);
    }
}
