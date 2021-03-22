package chess.controller;

import chess.domain.grid.Grid;
import chess.domain.grid.gridStrategy.GridStrategy;
import chess.domain.grid.gridStrategy.NormalGridStrategy;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private static final String START_COMMAND = "start";
    private GameState gameState;

    public ChessController() {
        this.gameState = new Ready();
    }

    public void run() {
        OutputView.printChessInstruction();
        GridStrategy gridStrategy = new NormalGridStrategy();
        Grid grid = new Grid(gridStrategy);
        do {
            String command = InputView.command();
            this.gameState = gameState.run(grid, command);
        }
        while (!gameState.isFinished());
    }
}
