package chess.controller.command;

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
