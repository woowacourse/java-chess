package chess.controller;

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
        while (inputView.inputStartCommand()) {
            Board board = new Board();

            outputView.printBoard(board.getValue());
        }

    }

    public boolean isRunning() {
        return inputView.inputStartCommand();
    }
}
