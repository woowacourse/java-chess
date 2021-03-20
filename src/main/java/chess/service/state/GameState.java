package chess.service.state;

import chess.domain.grid.Grid;

public interface GameState {
    public GameState run(Grid grid);

    public boolean isFinished();
}
