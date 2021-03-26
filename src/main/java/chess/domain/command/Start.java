package chess.domain.command;

import chess.domain.Game;

public class Start extends BasicCommand {

    @Override
    public Command run(final Game game, final String command) {
        game.init();
        return new Start();
    }

    @Override
    public boolean isStartCommand() {
        return true;
    }
}
