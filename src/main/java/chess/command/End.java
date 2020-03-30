package chess.command;

import chess.progress.Progress;

public class End extends Command {
    public End(String value) {
        super(value, End::doEndCommand);
    }

    private static Progress doEndCommand(String command) {
        if (command.equals("end")) {
            return Progress.END;
        }
        return Progress.ERROR;
    }
}
