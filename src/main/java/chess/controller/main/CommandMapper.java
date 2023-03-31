package chess.controller.main;

import chess.controller.Controller;
import java.util.Map;

public class CommandMapper {

    private final Map<ActionType, Controller> actions;

    public CommandMapper(Map<ActionType, Controller> actions) {
        this.actions = actions;
    }

    public Controller getController(ActionType type) {
        return actions.get(type);
    }
}
