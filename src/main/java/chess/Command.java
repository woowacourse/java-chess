package chess;

import java.util.Arrays;

public enum Command {
    START("start", false),
    END("end", false),
    MOVE("move", true),
    STATUS("status", true);

    private final String command;
    private final boolean isInGameCommand;

    Command(String command, boolean isInGameCommand) {
        this.command = command;
        this.isInGameCommand = isInGameCommand;
    }

    public static Command inGameCommandOf(String command) {
        return Arrays.stream(values())
                .filter(value -> value.command.equals(command.toLowerCase()) && value.isInGameCommand)
                .findFirst()
                .orElseThrow(CommandException::new);
    }

    public static Command beforeGameCommandOf(String command) {
        return Arrays.stream(values())
                .filter(value -> value.command.equals(command.toLowerCase()) && !value.isInGameCommand)
                .findFirst()
                .orElseThrow(CommandException::new);
    }

    public boolean isStart() {
        return this == START;
    }

    public boolean isNotStart() {
        return !isStart();
    }

    public boolean isEnd() {
        return this == END;
    }

    public boolean isMove() {
        return this == MOVE;
    }

    public boolean isStatus() {
        return this == STATUS;
    }

    public boolean isInGameCommand() {
        return isInGameCommand;
    }

    public boolean isNotInGameCommand() {
        return !isInGameCommand;
    }
}