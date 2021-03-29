package chess.domain.grid.gridStrategy;

import chess.domain.grid.Line;
import chess.domain.piece.Color;
import chess.domain.state.GameState;

import java.util.List;

public interface GridStrategy {
    List<Line> linesInInitGrid();

    GameState initGameState();

    Color turn();
}
