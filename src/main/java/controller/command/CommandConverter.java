package controller.command;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CommandConverter {
    public static final int MOVE_COMMAND_SOURCE_INDEX = 1;
    public static final int MOVE_COMMAND_DESTINATION_INDEX = 2;

    private static final int COMMAND_TYPE_INDEX = 0;
    private static final int COMMAND_FORM_MAX = 3;
    private static final int COMMAND_FORM_MIN = 1;
    private final CommandType commandType;

    private final Map<CommandType, Consumer<List<String>>> commandValidater = Map.of(
            CommandType.START, this::validateBasicCommands,
            CommandType.MOVE, this::validateMoveCommand,
            CommandType.END, this::validateBasicCommands,
            CommandType.STATUS, this::validateBasicCommands
    );

    public CommandConverter(List<String> inputs) {
        validateCommand(inputs);
        CommandType commandType = CommandType.from(inputs.get(COMMAND_TYPE_INDEX));
        commandValidater.get(commandType).accept(inputs);
        this.commandType = commandType;
    }

    private void validateCommand(List<String> inputs) {
        validateCommandForm(inputs, s -> s.size() > COMMAND_FORM_MAX);
    }

    private void validateCommandForm(List<String> inputs, Predicate<List<String>> predicate) {
        if (predicate.test(inputs)) {
            throw new IllegalArgumentException("잘못된 형식입니다.");
        }
    }

    private void validateMoveCommand(List<String> inputs) {
        validateCommandForm(inputs, s -> s.size() != COMMAND_FORM_MAX);
    }

    private void validateBasicCommands(List<String> inputs) {
        validateCommandForm(inputs, s -> s.size() != COMMAND_FORM_MIN);
    }

    public CommandType getCommandType() {
        return commandType;
    }

}
