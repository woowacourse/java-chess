package chess.domain.command;

import chess.domain.Game;

public class Status extends BasicCommand {

    @Override
    public Command run(final Game game, final String command) {
        return new Status();
    }

    @Override
    public boolean isStatusCommand() {
        return true;
    }
}
