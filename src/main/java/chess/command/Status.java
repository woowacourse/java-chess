package chess.command;

import chess.progress.Progress;

public class Status extends Command {
    public Status(String value) {
        super(value, Status::doStatusCommand);
    }

    private static Progress doStatusCommand(String command) {
        if (command.equals("status")) {
            return Progress.STATUS;
        }
        return Progress.ERROR;
    }
}
