package chess.domain.command;

import chess.domain.Game;

public abstract class BasicCommand implements Command {

    protected final Game game;

    public BasicCommand(final Game game) {
        this.game = game;
    }
}
