package chess.domain.command;

import java.util.Arrays;
import java.util.List;

public class Command {
    private static final List<String> NO_OPTION_COMMANDS = Arrays.asList("start", "end", "status");
    private static final String MOVE_OPTION_COMMAND = "move";
    private final String command;
    private final List<String> options;

    public Command(String statement) {
        this(statement.split(" "));
    }

    public Command(String[] args) {
        this(args[0], Arrays.asList(args)
                            .subList(1, args.length));
    }

    public Command(String command, List<String> options) {
        validateCommand(command, options);
        this.command = command;
        this.options = options;
    }

    private void validateCommand(String command, List<String> options) {
        if (isMoveOptionCommand(command, options)) {
            return;
        }
        if (isNoOptionCommand(command, options)) {
            return;
        }
        throw new IllegalArgumentException();
    }

    private boolean isNoOptionCommand(String command, List<String> options) {
        return NO_OPTION_COMMANDS.contains(command) && options.isEmpty();
    }

    private boolean isMoveOptionCommand(String command, List<String> options) {
        return MOVE_OPTION_COMMAND.equals(command) && options.size() == 2;
    }

    public List<String> getOptions() {
        return options;
    }

    public boolean isStart() {
        return "start".equals(command);
    }

    public boolean isEnd() {
        return "end".equals(command);
    }

    public boolean isMove() {
        return "move".equals(command);
    }

    public boolean isStatus() {
        return "status".equals(command);
    }
}
