package chess.domain.command;

import java.util.ArrayList;
import java.util.List;

public class CommandInput {
    private static final String MOVE = "move";
    private static final int MOVE_COMMAND_OPTION_LENGTH = 2;
    private static final int TARGET_INDEX = 0;
    private static final int DESTINATION_INDEX = 1;

    private final String command;
    private final List<String> options;

    public CommandInput(String command) {
        this(command, new ArrayList<>());
    }

    public CommandInput(String command, List<String> options) {
        if (command.equals(MOVE) && options.size() != MOVE_COMMAND_OPTION_LENGTH) {
            throw new IndexOutOfBoundsException();
        }
        this.command = command;
        this.options = options;
    }

    public boolean isSameCommand(String input) {
        return this.command.equals(input.toLowerCase());
    }

    public String getTarget() {
        return options.get(TARGET_INDEX);
    }

    public String getDestination() {
        return options.get(DESTINATION_INDEX);
    }
}
