package view;

import service.command.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CommandFormat {
    START(inputs -> new StartCommand()),
    MOVE(CommandFormat::createMoveCommand),
    END(inputs -> new EndCommand()),
    STATUS(inputs -> new StatusCommand());

    private static final Pattern MOVE_FORMAT = Pattern.compile("^move [a-z]\\d [a-z]\\d$");

    private static final String MOVE_COMMAND_DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int MOVE_SOURCE_INDEX = 1;
    private static final int TARGET_SOURCE_INDEX = 2;

    private static MoveCommand createMoveCommand(final String input) {
        final Matcher matcher = MOVE_FORMAT.matcher(input);
        if (matcher.matches()) {
            final List<String> inputs = Arrays.asList(input.split(MOVE_COMMAND_DELIMITER));
            return new MoveCommand(inputs.get(MOVE_SOURCE_INDEX), inputs.get(TARGET_SOURCE_INDEX));
        }
        throw new IllegalArgumentException();
    }

    private final Function<String, ChessCommand> commandFunction;

    CommandFormat(final Function<String, ChessCommand> commandFunction) {
        this.commandFunction = commandFunction;
    }

    public static ChessCommand createCommand(final String input) {
        final String command = input.split(MOVE_COMMAND_DELIMITER)[COMMAND_INDEX];

        try {
            final CommandFormat commandFormat = valueOf(command.toUpperCase());
            return commandFormat.commandFunction.apply(input);
        } catch (final IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 커맨드입니다.");
        }
    }
}
