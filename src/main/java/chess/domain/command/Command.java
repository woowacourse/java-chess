package chess.domain.command;

import java.util.Collections;
import java.util.List;

public class Command {

    private final CommandType command;
    private final List<String> flags;

    private Command(CommandType command, List<String> flags) {
        this.command = command;
        this.flags = flags;
    }

    public static Command from(List<String> input) {
        CommandType commandType = CommandType.from(input.get(0));
        List<String> flags = input.subList(1, input.size());

        return new Command(commandType, flags);
    }

    public boolean isStart() {
        return command.equals(CommandType.START);
    }

    public boolean isNotEnd() {
        return !command.equals(CommandType.END);
    }

    public boolean isMove() {
        return command.equals(CommandType.MOVE);
    }

    public boolean isStatus() {
        return command.equals(CommandType.STATUS);
    }

    public List<String> getFlags() {
        return Collections.unmodifiableList(flags);
    }
}
