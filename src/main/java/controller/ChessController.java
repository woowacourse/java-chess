package controller;

import domain.ChessBoard;
import view.OutputView;

public class ChessController {
    private final OutputView outputView;

    public ChessController(final OutputView outputView) {
        this.outputView = outputView;
    }

    public void run() {
        ChessBoard chessBoard = new ChessBoard();
        outputView.printChessBoard(chessBoard);
    }
}
