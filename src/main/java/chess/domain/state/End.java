package chess.domain.state;

import chess.domain.grid.Grid;

public final class End extends Finished {
    @Override
    public GameState run(final Grid grid, final String input) {
        return new End();
    }
}
