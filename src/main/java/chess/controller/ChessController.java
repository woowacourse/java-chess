package chess.controller;

import chess.service.ChessService;
import chess.service.state.GameState;
import chess.service.state.Ready;
import chess.view.OutputView;

public class ChessController {
    private final ChessService chessService;
    private GameState gameState;

    public ChessController(ChessService chessService) {
        this.chessService = chessService;
        this.gameState = new Ready();
    }

    public void run() {
        OutputView.printChessInstruction();
        while (!gameState.isFinished()) {
//            changeStateByCommand();
            this.gameState = gameState.run(chessService);
        }
    }

    private void changeStateByCommand() {
//        String command = InputView.command();
//        gameState = Command.gameState(command);
    }
}
