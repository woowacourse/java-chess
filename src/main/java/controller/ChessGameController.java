package controller;

import domain.ChessBoardFactory;
import view.OutputView;

public class ChessGameController {

    private final OutputView outputView;

    public ChessGameController() {
        outputView = new OutputView();
    }

    public void run() {
        outputView.printChessBoard(ChessBoardFactory.generate());
    }
}
