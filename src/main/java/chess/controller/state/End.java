package chess.controller.state;

import chess.domain.game.Game;

public class End extends State {
    End(final Game game) {
        super(game);
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
