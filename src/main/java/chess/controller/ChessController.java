package chess.controller;

import chess.controller.state.GameState;
import chess.controller.state.Ready;
import chess.domain.grid.Grid;
import chess.view.OutputView;

public class ChessController {
    private GameState gameState;

    public ChessController() {
        this.gameState = new Ready();
    }

    public void run() {
        OutputView.printChessInstruction();
        Grid grid = new Grid();
        do {
            this.gameState = gameState.run(grid);
        }
        while (!gameState.isFinished());
    }
}
