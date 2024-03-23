package chess.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Command {
    private final String command;

    public Command(String command) {
        validateCommand(command);
        this.command = command;
    }

    private void validateCommand(String inputCommand) {
        List<String> commands = Arrays.stream(CommandType.values()).map(CommandType::getCommandType).toList();
        if (!commands.contains(inputCommand)) {
            throw new IllegalArgumentException(String.format("%s는 올바른 커맨드가 아닙니다. start, move, end 중 하나여야 합니다.", inputCommand));
        }
    }

    public boolean isCommand(CommandType commandType) {
        return Objects.equals(command, commandType.getCommandType());
    }
}
