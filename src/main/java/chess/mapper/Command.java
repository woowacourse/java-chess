package chess.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Command {

    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String value;

    Command(final String value) {
        this.value = value;
    }

    public static boolean isEnd(final String command) {
        return Command.END.value.equals(command);
    }

    public static boolean isStart(final String command) {
        return Command.START.value.equals(command);
    }

    public static boolean isMove(final String command) {
        return command.startsWith(Command.MOVE.value);
    }

    public static boolean isStatus(final String command) {
        return Command.STATUS.value.equals(command);
    }

    public static List<String> words() {
        return Stream.of(values())
                .map(command -> command.value)
                .collect(Collectors.toList());
    }
}
