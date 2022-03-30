package chess.domain.command;

import java.util.List;
import java.util.function.Predicate;

public enum CommandConditions {
    START_CONDITION(
            (input) -> input.size() == 1 && input.get(0).equals("start")
    ),
    END_CONDITION(
            (input) -> input.size() == 1 && input.get(0).equals("end")
    ),
    STATUS_CONDITION(
            (input) -> input.size() == 1 && input.get(0).equals("status")
    ),
    MOVE_CONDITION(
            (input) -> input.size() == 3 && input.get(0).equals("move")
                    && input.get(1).length() == 2 && input.get(2).length() == 2);

    private final Predicate<List<String>> predicate;

    CommandConditions(Predicate<List<String>> predicate) {
        this.predicate = predicate;
    }

    public boolean doesSatisfy(List<String> input) {
        return predicate.test(input);
    }
}
