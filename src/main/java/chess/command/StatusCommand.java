package chess.command;

import chess.progress.Progress;

public class StatusCommand implements Command {
    private static final String COMMAND = "status";

    private final String title;

    public StatusCommand(String title) {
        this.title = title;
    }

    @Override
    public Progress conduct() {
        if (COMMAND.equals(title)) {
            return Progress.STATUS;
        }
        return Progress.ERROR;
    }

    public static boolean isStatus(String command) {
        String lowerCaseCommand = command.toLowerCase();
        return COMMAND.equals(lowerCaseCommand);
    }
}
