package chess.controller;

import chess.domain.Board;
import chess.view.Commend;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        outputView.printStartMessage();
        Commend commend = Commend.inputToCommend(inputView.readCommend());
        if (commend == Commend.START) {
            outputView.printBoard(new Board());
        }
    }
}
