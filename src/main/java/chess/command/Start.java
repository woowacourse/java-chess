package chess.command;

import chess.progress.Progress;

public class Start extends Command {
    public Start(String value) {
        super(value, Start::getProgress);
    }

    private static Progress getProgress(String command) {
        if (command.equals("start")) {
            return Progress.CONTINUE;
        }
        return Progress.ERROR;
    }
}
