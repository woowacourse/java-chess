package chess.domain.command;

import chess.view.InputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandInput {
    private static final String MOVE = "move";
    private static final int MOVE_COMMAND_OPTION_LENGTH = 2;
    private static final int TARGET_INDEX = 0;
    private static final int DESTINATION_INDEX = 1;
    private static final String DELIMITER = " ";
    private static final int ONLY_COMMAND = 1;
    private static final int OPTION_TARGET = 1;
    private static final int OPTION_DESTINATION = 2;

    private final String command;
    private final List<String> options;

    private CommandInput(String command) {
        this(command, new ArrayList<>());
    }

    private CommandInput(String command, List<String> options) {
        if (command.equals(MOVE) && options.size() != MOVE_COMMAND_OPTION_LENGTH) {
            throw new IndexOutOfBoundsException();
        }
        this.command = command;
        this.options = options;
    }

    public static CommandInput create() {
        String input = InputView.inputCommand();
        List<String> inputs = Arrays.asList(input.split(DELIMITER));
        String command = inputs.get(0);

        if (inputs.size() == ONLY_COMMAND) {
            return new CommandInput(command);
        }
        return new CommandInput(command, Arrays.asList(inputs.get(OPTION_TARGET), inputs.get(OPTION_DESTINATION)));
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
