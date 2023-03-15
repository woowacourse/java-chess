package chess.controller;

import chess.domain.PiecesPosition;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final OutputView outputView;
    private final InputView inputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        inputView.readCommand();
    }



    public void printBoard() {
        PiecesPosition piecesPosition = new PiecesPosition();

        outputView.printChessState(piecesPosition.getPiecesPosition());
    }
}
