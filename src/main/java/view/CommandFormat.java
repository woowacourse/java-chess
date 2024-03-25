package view;

import service.command.*;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandFormat {

    private static final String START_FORMAT = "start";
    private static final String END_FORMAT = "end";
    private static final String STATUS_FORMAT = "status";
    private static final Pattern MOVE_FORMAT = Pattern.compile("^move [a-z]\\d [a-z]\\d$");
    private static final String MOVE_COMMAND_DELIMITER = " ";
    private static final int MOVE_SOURCE_INDEX = 1;
    private static final int TARGET_SOURCE_INDEX = 2;

    private CommandFormat() {
    }

    public static ChessCommand createCommand(final String input) {
        if (END_FORMAT.equals(input)) {
            return new EndCommand();
        }
        if (START_FORMAT.equals(input)) {
            return new StartCommand();
        }
        if (STATUS_FORMAT.equals(input)) {
            return new StatusCommand();
        }

        final Matcher matcher = MOVE_FORMAT.matcher(input);
        if (matcher.matches()) {
            final List<String> inputs = Arrays.asList(input.split(MOVE_COMMAND_DELIMITER));
            return new MoveCommand(inputs.get(MOVE_SOURCE_INDEX), inputs.get(TARGET_SOURCE_INDEX));
        }

        throw new IllegalArgumentException("잘못된 커맨드입니다.");
    }
}
