package chess.view;

import java.util.regex.Pattern;

public enum GameCommand {

    START("start"),
    END("end"),
    MOVE("move"),
    ;

    private static final Pattern MOVE_COMMAND_PATTERN = Pattern.compile("^move [a-h][1-8] [a-h][1-8]$");

    private final String value;

    GameCommand(final String value) {
        this.value = value;
    }

    public static boolean isStartCommand(final String rawInput) {
        return START.getValue().equals(rawInput);
    }

    public static boolean isEndCommand(final String rawInput) {
        return END.getValue().equals(rawInput);
    }

    public static boolean isMoveCommand(final String rawInput) {
        return MOVE.getValue().equals(rawInput);
    }

    public static boolean isMovePattern(final String rawInput) {
        return MOVE_COMMAND_PATTERN.matcher(rawInput).matches();
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
