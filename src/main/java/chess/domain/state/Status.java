package chess.domain.state;

import chess.domain.grid.Grid;

public final class Status extends Finished {
    @Override
    public final GameState run(final Grid grid, final String command) {
        return new End();
    }
}
