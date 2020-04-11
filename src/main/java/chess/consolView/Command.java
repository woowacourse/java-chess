package chess.consolView;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public enum Command {
    START(Command::isStart),
    END(Command::isEnd),
    MOVE(Command::isMove),
    STATUS(Command::isStatus);

    private static final String MOVE_PATTERN = "move ([a-h][1-8]) ([a-h][1-8])";
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";
    private static final String STATUS_COMMAND = "status";

    private final Predicate<String> compare;

    Command(Predicate<String> compare) {
        this.compare = compare;
    }

    public static boolean isStart(String command) {
        return START_COMMAND.equalsIgnoreCase(command);
    }

    public static boolean isEnd(String command) {
        return END_COMMAND.equalsIgnoreCase(command);
    }

    public static boolean isMove(String command) {
        return Pattern.matches(MOVE_PATTERN, command.toLowerCase());
    }

    public static boolean isStatus(String command) {
        return STATUS_COMMAND.equalsIgnoreCase(command);
    }

    public static Command findBy(String value) {
        return Arrays.stream(Command.values())
                .filter(command -> command.compare.test(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}