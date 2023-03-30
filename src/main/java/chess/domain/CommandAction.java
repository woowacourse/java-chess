package chess.domain;

import chess.controller.Action;
import chess.controller.Command;
import chess.controller.CommandManagement;

import java.util.Map;

public class CommandAction {

    private final Map<Command, Action> commandActions;

    public CommandAction(final Map<Command, Action> commandActions) {
        this.commandActions = commandActions;
    }

    public void execute(final CommandManagement commandManagement) {
        commandActions.get(commandManagement.getCommand()).execute(commandManagement);
    }

}
