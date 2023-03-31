package chess.controller.main;

import java.util.Map;

public class CommandMapper {

    private final Map<ActionType, Action> actions;

    public CommandMapper(Map<ActionType, Action> actions) {
        this.actions = actions;
    }

    public Action getAction(ActionType type) {
        return actions.get(type);
    }
}
