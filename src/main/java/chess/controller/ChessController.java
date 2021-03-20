package chess.controller;

import chess.domain.grid.Grid;
import chess.service.state.GameState;
import chess.service.state.Ready;
import chess.view.OutputView;

public class ChessController {
    private GameState gameState;

    public ChessController() {
        this.gameState = new Ready();
    }

    public void run() {
        OutputView.printChessInstruction();
        while (!gameState.isFinished()) {
            this.gameState = gameState.run(new Grid());
        }
    }
}
