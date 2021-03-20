package chess.service.state;

import chess.domain.grid.Grid;

public class End extends Finished {
    @Override
    public GameState run(Grid grid) {
        return new End();
    }
}
