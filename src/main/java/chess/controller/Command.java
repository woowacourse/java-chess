package chess.controller;

import java.util.Arrays;
import java.util.List;

public final class Command {
    private static final int COMMAND_INDEX = 0;
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final String COMMAND_ERROR_MESSAGE = "잘못된 명령어 입력입니다.";

    private final CommandType type;
    private final List<String> commands;

    public Command(final CommandType type, final List<String> commands) {
        this.type = type;
        this.commands = commands;
    }

    public static Command findCommand(final List<String> commands) {
        final CommandType type = Arrays.stream(CommandType.values())
                .filter(e -> e.name().equalsIgnoreCase(commands.get(COMMAND_INDEX)))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(COMMAND_ERROR_MESSAGE));
        return new Command(type, commands);
    }

    public boolean isStart() {
        return type == CommandType.START;
    }

    public boolean isMove() {
        return type == CommandType.MOVE;
    }

    public boolean isEnd() {
        return type == CommandType.END;
    }

    public boolean isCorrectWhenMove() {
        return commands.size() == MOVE_COMMAND_SIZE;
    }

    public List<String> getCommands() {
        return List.copyOf(commands);
    }
}
