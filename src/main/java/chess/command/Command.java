package chess.command;

import chess.progress.Progress;

public class Command {
    private final String name;
    private final CommandConductable commandConductable;

    public Command(String value, CommandConductable commandConductable) {
        this.name = value;
        this.commandConductable = commandConductable;
    }

    public Progress conduct() {
        return commandConductable.couduct(name);
    }

    public String getName() {
        return name;
    }
}


