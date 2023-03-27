package chess.domain;

import chess.controller.Action;
import chess.controller.Command;
import chess.controller.Commands;

import java.util.Map;

public class CommandAction {

    private final Map<Command, Action> commandActions;

    public CommandAction(final Map<Command, Action> commandActions) {
        this.commandActions = commandActions;
    }

    public void execute(final Commands commands) {
        commandActions.get(commands.getCommand()).execute(commands);
    }

}
