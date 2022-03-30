package chess.domain.command;

import java.util.List;
import java.util.function.Predicate;

public enum CommandConditions {
    START_CONDITION(
            (input) -> input.size() == CommandConditions.SINGLE_COMMAND_SIZE
                    && input.get(CommandConditions.PREFIX_INDEX).equals(CommandConditions.START_PREFIX)
    ),
    END_CONDITION(
            (input) -> input.size() == CommandConditions.SINGLE_COMMAND_SIZE
                    && input.get(CommandConditions.PREFIX_INDEX).equals(CommandConditions.END_PREFIX)
    ),
    STATUS_CONDITION(
            (input) -> input.size() == CommandConditions.SINGLE_COMMAND_SIZE
                    && input.get(CommandConditions.PREFIX_INDEX).equals(CommandConditions.STATUS_PREFIX)
    ),
    MOVE_CONDITION(
            (input) -> input.size() == CommandConditions.MOVE_COMMAND_SIZE
                    && input.get(CommandConditions.PREFIX_INDEX).equals(CommandConditions.MOVE_PREFIX)
                    && input.get(1).length() == CommandConditions.POSITION_SIZE
                    && input.get(2).length() == CommandConditions.POSITION_SIZE);

    private static final int PREFIX_INDEX = 0;
    private static final int SINGLE_COMMAND_SIZE = 1;
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int POSITION_SIZE = 2;

    private static final String START_PREFIX = "start";
    private static final String END_PREFIX = "end";
    private static final String STATUS_PREFIX = "status";
    private static final String MOVE_PREFIX = "move";

    private final Predicate<List<String>> predicate;

    CommandConditions(Predicate<List<String>> predicate) {
        this.predicate = predicate;
    }

    public boolean doesSatisfy(List<String> input) {
        return predicate.test(input);
    }
}
