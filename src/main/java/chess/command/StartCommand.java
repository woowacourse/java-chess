package chess.command;

import chess.progress.Progress;

public class StartCommand implements Command {
    private static final String COMMAND = "start";

    private final String value;

    StartCommand(String value) {
        this.value = value;
    }

    static boolean isStart(String input) {
        return COMMAND.equals(input);
    }

    @Override
    public Progress conduct() {
        if (COMMAND.equals(value)) {
            return Progress.CONTINUE;
        }
        return Progress.ERROR;
    }
}
