package chess.command;

import chess.progress.Progress;

public class Status extends Command {

    public static final String COMMAND = "status";

    public Status(String value) {
        super(value, Status::doStatusCommand);
    }

    private static Progress doStatusCommand(String command) {
        if (command.equals(COMMAND)) {
            return Progress.STATUS;
        }
        return Progress.ERROR;
    }
}
