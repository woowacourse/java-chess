package chess.controller;

import chess.controller.state.Ready;
import chess.controller.state.ChessGameState;
import chess.view.InputView;

public class ChessController {

    private static final String REGEX = " ";
    private ChessGameState chessGameState;

    public ChessController() {
        this.chessGameState = new Ready();
    }

    public void start() {
        chessGameState = chessGameState.start();

        while (!chessGameState.isEnded()) {
            String[] command = getCommand();
            chessGameState = ChessExecution.from(command[0]).run(chessGameState, command);
        }
    }

    private String[] getCommand() {
        InputView inputView = InputView.getInstance();
        return inputView.scanCommand().split(REGEX);
    }
}
