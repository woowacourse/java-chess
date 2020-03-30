package chess.command;

import chess.progress.Progress;

public class Start extends Command {
    private static final String COMMAND = "start";

    public Start(String value) {
        super(value, Start::doStartCommand);
    }

    private static Progress doStartCommand(String command) {
        if (COMMAND.equals(command)) {
            return Progress.CONTINUE;
        }
        return Progress.ERROR;
    }
}
