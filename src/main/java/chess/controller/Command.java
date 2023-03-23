package chess.controller;

import java.util.Arrays;
import java.util.List;

public enum Command {

    START("start", 1),
    END("end", 1),
    MOVE("move", 3),
    STATUS("status", 1);

    private static final int COMMAND_INDEX = 0;
    private final String command;
    private final int commandsCount;

    Command(final String command, final int commandsCount) {
        this.command = command;
        this.commandsCount = commandsCount;
    }

    public static Command getCommand(final List<String> commandWithArguments) {
        Command findCommand = Arrays.stream(Command.values())
                .filter(commands -> commands.command.equals(commandWithArguments.get(COMMAND_INDEX)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 커맨드입니다."));

        validate(findCommand, commandWithArguments);
        return findCommand;
    }

    private static void validate(Command findCommand, List<String> commandWithArguments) {
        if (findCommand.commandsCount != commandWithArguments.size()) {
            throw new IllegalArgumentException("커맨드 인자 개수가 올바르지 않습니다.");
        }
    }

    public boolean isNotEnd() {
        return this != END;
    }

}
