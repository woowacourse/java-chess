package model;

import constant.ErrorCode;
import exception.InvalidCommandException;
import java.util.Arrays;
import java.util.regex.Pattern;

public enum Command {

    START(Pattern.compile("start")),
    MOVE(Pattern.compile("move")),
    POSITION(Pattern.compile("[a-hA-H][1-8]")),
    END(Pattern.compile("end"));

    public static final int HEAD_INDEX = 0;
    public static final int START_COMMAND_SIZE = 1;
    public static final int MOVE_COMMAND_SIZE = 3;
    public static final int END_COMMAND_SIZE = 1;
    public static final int CURRENT_INDEX = 1;
    public static final int NEXT_INDEX = 2;


    private final Pattern pattern;

    Command(final Pattern pattern) {
        this.pattern = pattern;
    }

    public static void validate(String input) {
        boolean match = Arrays.stream(values())
                .anyMatch(command -> command.pattern.matcher(input).matches());
        if (!match) {
            throw new InvalidCommandException(ErrorCode.INVALID_COMMAND);
        }
    }

    public static Command from(String value) {
        return Arrays.stream(values())
                .filter(command -> command.pattern.matcher(value).matches())
                .findFirst()
                .orElseThrow(() -> new InvalidCommandException(ErrorCode.INVALID_COMMAND));
    }
}
