package chess.view;

import java.util.Arrays;
import java.util.List;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move");

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    public static Command from(final List<String> command) {
        validateCommand(command);
        return Arrays.stream(values())
                .filter(it -> it.value.equals(command.get(0)))
                .findFirst()
                .orElseThrow(CommandException::new);
    }

    private static void validateCommand(final List<String> command) {
        if (command.size() == 1) {
            validateStartAndEnd(command.get(0));
            return;
        } else if (command.size() == 3) {
            validateMove(command);
            return;
        }
        throw new CommandException();
    }

    private static void validateStartAndEnd(final String command) {
        if (command.equals(START.value) || command.equals(END.value)) {
            return;
        }
        throw new CommandException();
    }

    private static void validateMove(final List<String> command) {
        final String moveCommand = command.get(0);
        if (!moveCommand.equals(MOVE.value)) {
            throw new CommandException();
        }
        validateMoveSource(command.get(1));
        validateMoveSource(command.get(2));
    }

    private static void validateMoveSource(final String source) {
        if (source.length() != 2) {
            throw new CommandException();
        }
    }

    public boolean isStartCommand() {
        return this.equals(START);
    }

    public boolean isEndCommand() {
        return this.equals(END);
    }

    public boolean isMoveCommand() {
        return this.equals(MOVE);
    }
}
