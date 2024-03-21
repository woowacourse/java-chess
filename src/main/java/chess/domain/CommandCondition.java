package chess.domain;

import java.util.List;

public class CommandCondition {
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private final List<String> condition;

    public CommandCondition(List<String> condition) {
        this.condition = condition;
    }

    public String getSource() {
        return condition.get(SOURCE_INDEX);
    }

    public String getTarget() {
        return condition.get(TARGET_INDEX);
    }
}
