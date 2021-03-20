package chess.controller.state;

import chess.domain.grid.Grid;

public interface GameState {
    GameState run(final Grid grid);

    boolean isFinished();
}
