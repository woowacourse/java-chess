package chess.domain.command;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public enum CommandType {
    START("start", StartCommand::new),
    MOVE("move", MoveCommand::new),
    END("end", EndCommand::new),
    STATUS("status", StatusCommand::new);

    private static final int COMMAND_HEAD_INDEX = 0;
    private final String command;
    private final Function<List<String>, Command> commandGenerator;

    CommandType(String command, Function<List<String>, Command> commandGenerator) {
        this.command = command;
        this.commandGenerator = commandGenerator;
    }

    public static CommandType getCommandType(List<String> commands) {
        String commandHead = commands.get(COMMAND_HEAD_INDEX);
        return Arrays.stream(CommandType.values())
                .filter(c -> c.isEqual(commandHead))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어 입니다."));
    }

    public boolean isEqual(String other) {
        return command.equals(other);
    }

    public Command getCommand(List<String> commands) {
        return commandGenerator.apply(commands);
    }
}