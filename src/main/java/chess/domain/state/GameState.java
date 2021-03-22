package chess.domain.state;

import chess.domain.grid.Grid;

public interface GameState {
    GameState run(final Grid grid, final String input);

    boolean isFinished();
}
