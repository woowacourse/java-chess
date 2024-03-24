package chess.domain;

import java.util.Arrays;
import java.util.List;

public class Command {
    private final CommandType command;
    private static String source;
    private static String target;

    private Command(CommandType command) {
        this.command = command;
    }

    public static Command of(List<String> commands) {
        String inputCommand = commands.get(0);
        validateCommand(inputCommand);
        if (inputCommand.equals(CommandType.MOVE.getCommandType())) {
            validateMove(commands);
        }
        return new Command(CommandType.valueByCommandType(inputCommand));
    }

    private static void validateCommand(String inputCommand) {
        List<String> commands = Arrays.stream(CommandType.values()).map(CommandType::getCommandType).toList();
        if (!commands.contains(inputCommand)) {
            throw new IllegalArgumentException(String.format("%s는 올바른 커맨드가 아닙니다. start, move, end 중 하나여야 합니다.", inputCommand));
        }
    }

    private static void validateMove(List<String> commands) {
        if (commands.size() != 3) {
            throw new IllegalArgumentException("move 커맨드는 'move source위치 target위치' 3개의 인자로 이루어져야 합니다.");
        }
        source = commands.get(1);
        target = commands.get(2);
    }

    public boolean isCommand(CommandType commandType) {
        return command.equals(commandType);
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }
}
