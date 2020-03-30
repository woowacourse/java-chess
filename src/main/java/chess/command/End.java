package chess.command;

import chess.progress.Progress;

public class End extends Command {
    public static final String COMMAND = "end";

    public End(String value) {
        super(value, End::doEndCommand);
    }

    private static Progress doEndCommand(String command) {
        if (command.equals(COMMAND)) {
            return Progress.END;
        }
        return Progress.ERROR;
    }
}
