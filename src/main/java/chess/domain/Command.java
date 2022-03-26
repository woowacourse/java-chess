package chess.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static boolean isEnd(String command) {
        return Command.END.value.equals(command);
    }

    public static boolean isStart(String command) {
        return Command.START.value.equals(command);
    }

    public static boolean isMove(String command) {
        return command.startsWith(Command.MOVE.value);
    }

    public static boolean isStatus(String command) {
        return Command.STATUS.value.equals(command);
    }

    public static List<String> words() {
        return Stream.of(values())
                .map(command -> command.value)
                .collect(Collectors.toList());
    }
}
