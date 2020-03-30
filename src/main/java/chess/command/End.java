package chess.command;

import chess.progress.Progress;

public class End extends Command {
    private static final String COMMAND = "end";

    public End(String value) {
        super(value, End::doEndCommand);
    }

    private static Progress doEndCommand(String command) {
        if (command.equals(COMMAND)) {
            return Progress.END;
        }
        return Progress.ERROR;
    }

    public static boolean isEnd(String command) {
        String lowerCaseCommand = command.toLowerCase();
        if (COMMAND.equals(lowerCaseCommand)) {
            return true;
        }
        return false;
    }
}
