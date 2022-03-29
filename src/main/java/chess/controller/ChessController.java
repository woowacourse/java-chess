package chess.controller;

import chess.controller.state.Ready;
import chess.controller.state.ChessGameState;
import chess.view.InputView;

public class ChessController {

    private ChessGameState chessGameState;

    public ChessController() {
        this.chessGameState = new Ready();
    }

    public void start() {
        chessGameState = chessGameState.start();

        while (!chessGameState.isEnded()) {
            InputView inputView = InputView.getInstance();
            String[] command = inputView.scanCommand().split(" ");
            chessGameState = ChessExecution.from(command[0]).run(chessGameState, command);
        }
    }
}
