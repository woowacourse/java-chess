package domain.command;

import java.util.List;

public class EndCommand implements Command {
    public static EndCommand END_COMMAND = new EndCommand();

    private EndCommand() {}
}
