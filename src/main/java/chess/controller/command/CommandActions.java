package chess.controller.command;

import java.util.Map;

public class CommandActions {

    private final Map<CommandType, Action> commandActions;

    public CommandActions(final Map<CommandType, Action> commandActions) {
        this.commandActions = commandActions;
    }

    public void execute(final Command command) {
        commandActions.get(command.getCommandType()).execute(command);
    }
}
