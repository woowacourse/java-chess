package chess.domain.command;

import chess.domain.Game;

public abstract class BasicCommand implements Command {

    @Override
    abstract public Command run(final Game game, final String command);

    @Override
    public boolean isStartCommand() {
        return false;
    }

    @Override
    public boolean isEndCommand() {
        return false;
    }

    @Override
    public boolean isStatusCommand() {
        return false;
    }

    @Override
    public boolean isMoveCommand() {
        return false;
    }
}
