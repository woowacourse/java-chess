package chess.controller.state;

import chess.domain.grid.Grid;

public final class End extends Finished {
    @Override
    public GameState run(final Grid grid) {
        return new End();
    }
}
