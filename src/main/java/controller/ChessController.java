package controller;

import static view.StartOrEndCommand.START;

import domain.chessboard.ChessBoard;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printCommandMessage();
        while (inputView.enterStartOrEnd() == START) {
            ChessBoard chessBoard = new ChessBoard();
        }
    }
}
