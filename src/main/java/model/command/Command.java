package model.command;

import constant.ErrorCode;
import exception.InvalidCommandException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public enum Command {

    START(Pattern.compile("start"), 0),
    MOVE(Pattern.compile("move"), 2),
    POSITION(Pattern.compile("[a-hA-H][1-8]"), 0),
    END(Pattern.compile("end"), 0);

    public static final int HEAD_INDEX = 0;

    private final Pattern pattern;
    private final int bodySize;

    Command(final Pattern pattern, final int bodySize) {
        this.pattern = pattern;
        this.bodySize = bodySize;
    }

    public static Command from(String value) {
        return Arrays.stream(values())
                .filter(command -> command.pattern.matcher(value).matches())
                .findFirst()
                .orElseThrow(() -> new InvalidCommandException(ErrorCode.INVALID_COMMAND));
    }

    public static void validate(List<String> values) {
        if (values == null || values.isEmpty()) {
            throw new InvalidCommandException(ErrorCode.INVALID_COMMAND);
        }
        values.forEach(Command::validate);
        Command command = Command.from(values.get(HEAD_INDEX));
        if (values.size() != command.bodySize) {
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

    public boolean isEqualToBodySize(int targetSize) {
        return bodySize == targetSize;
    }
}
