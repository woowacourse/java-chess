package chess.domain.command;

import chess.domain.Game;

public class End extends BasicCommand {
    @Override
    public Command run(final Game game, final String command) {
        game.end();

        return new End();
    }

    @Override
    public boolean isEndCommand() {
        return true;
    }
}
