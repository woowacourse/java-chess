package chess.view;

import java.util.List;
import java.util.function.Predicate;

public enum Command {

    START(command -> command.equals("start")),
    MOVE(command -> command.contains("move")),
    END(command -> command.equals("end")),
    STATUS(command -> command.equals("status"));

    private static final String COMMAND_DELIMITER = " ";
    private static final String LOCATION_DELIMITER = "";
    private static final int SOURCE = 1;
    private static final int TARGET = 2;

    private final Predicate<String> command;

    Command(Predicate<String> command) {
        this.command = command;
    }

    public boolean isValue(String input) {
        return this.command.test(input);
    }

    public static String[] toMoveSourceFormat(String input) {
        List<String> moveInfo = List.of(input.split(COMMAND_DELIMITER));
        return moveInfo.get(SOURCE).split(LOCATION_DELIMITER);
    }

    public static String[] toMoveTargetFormat(String input) {
        List<String> moveInfo = List.of(input.split(COMMAND_DELIMITER));
        return moveInfo.get(TARGET).split(LOCATION_DELIMITER);
    }
}
