package chess.domain.state;

import chess.domain.grid.Grid;

public final class Ready implements GameState {

    @Override
    public final GameState run(final Grid grid, String command) {
        return new WhiteTurn();
    }

    @Override
    public final boolean isFinished() {
        return false;
    }
}
