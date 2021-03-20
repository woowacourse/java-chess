package chess.controller;

import chess.controller.state.GameState;
import chess.controller.state.Ready;
import chess.domain.grid.Grid;
import chess.domain.grid.gridStrategy.GridStrategy;
import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.view.OutputView;

public class ChessController {
    private GameState gameState;

    public ChessController() {
        this.gameState = new Ready();
    }

    public void run() {
        OutputView.printChessInstruction();
        GridStrategy gridStrategy = new NormalGridStrategy();
        Grid grid = new Grid(gridStrategy);
        do {
            this.gameState = gameState.run(grid);
        }
        while (!gameState.isFinished());
    }
}
