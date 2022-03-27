package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        ChessGame chessGame = new ChessGame();
        while (inputView.inputStartCommand()) {
            chessGame.start();

            outputView.printBoard(chessGame.getBoard().getValue());
        }

    }

    public boolean isRunning() {
        return inputView.inputStartCommand();
    }
}
