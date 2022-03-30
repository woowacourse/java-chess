package chess.domain.command;

import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {

    public static final String DELIMITER = " ";
    public static final int COMMAND_INDEX = 0;
    public static final int POSITION_FROM = 0;
    public static final int POSITION_TO = 1;
    public static final int POSITION_START = 1;
    public static final int POSITION_END = 3;

    private final CommandType commandType;
    private final List<String> commandArguments;

    private Command(CommandType commandType, List<String> commandArguments) {
        this.commandType = commandType;
        this.commandArguments = commandArguments;
    }

    public static Command from(String commands) {
        List<String> splitCommands = Arrays.stream(commands.split(DELIMITER))
            .collect(Collectors.toList());
        List<String> positions = new ArrayList<>();

        CommandType commandType = CommandType.findByCommand(splitCommands.get(COMMAND_INDEX));
        if (commandType == CommandType.MOVE) {
            positions = splitCommands.subList(POSITION_START, POSITION_END);
        }

        return new Command(commandType, positions);
    }

    public boolean isStart() {
        return commandType == CommandType.START;
    }

    public boolean isEnd() {
        return commandType == CommandType.END;
    }

    public boolean isMove() {
        return commandType == CommandType.MOVE;
    }

    public boolean isStatus() {
        return commandType == CommandType.STATUS;
    }

    public Position from() {
        return Position.from(commandArguments.get(POSITION_FROM));
    }

    public Position to() {
        return Position.from(commandArguments.get(POSITION_TO));
    }
}
