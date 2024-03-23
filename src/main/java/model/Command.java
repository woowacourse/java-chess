package model;

import constant.ErrorCode;
import exception.InvalidCommandException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public enum Command {

    START(Pattern.compile("start"), 1),
    MOVE(Pattern.compile("move"), 3),
    POSITION(Pattern.compile("[a-hA-H][1-8]"), 1),
    END(Pattern.compile("end"), 1);

    public static final int HEAD_INDEX = 0;
    public static final int CURRENT_INDEX = 1;
    public static final int NEXT_INDEX = 2;


    private final Pattern pattern;
    private final int size;

    Command(final Pattern pattern, final int size) {
        this.pattern = pattern;
        this.size = size;
    }

    public static void validate(List<String> values) {
        if (values == null || values.isEmpty()) {
            throw new InvalidCommandException(ErrorCode.INVALID_COMMAND);
        }
        values.forEach(Command::validate);
        Command command = Command.from(values.get(HEAD_INDEX));
        if (values.size() != command.size) {
            throw new InvalidCommandException(ErrorCode.INVALID_COMMAND);
        }
    }

    private static void validate(String input) {
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

    public boolean isAvailableSize(int targetSize) {
        return size == targetSize;
    }
}
