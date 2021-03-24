package chess.domain.state;

import chess.domain.grid.Grid;
import chess.domain.position.Position;

public interface GameState {
    GameState start();

    GameState end();

    GameState move(Grid grid, Position source, Position target);

    GameState status();

    boolean isFinished();
}
