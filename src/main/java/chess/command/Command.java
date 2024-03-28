package chess.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Command {
    private final CommandType type;
    private final List<String> arguments;

    public Command(CommandType type, List<String> arguments) {
        this.type = type;
        this.arguments = new ArrayList<>(arguments);
    }

    public static Command createNoArgCommand(CommandType commandType) {
        return new Command(commandType, Collections.emptyList());
    }

    public boolean isStart() {
        return type == CommandType.START;
    }

    public boolean isNotStart() {
        return !isStart();
    }

    public boolean isNotStartOrEnd() {
        return isNotStart() && isNotEnd();
    }

    public boolean isMove() {
        return type == CommandType.MOVE;
    }

    public boolean isNotEnd() {
        return type != CommandType.END;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
