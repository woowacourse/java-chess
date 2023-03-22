package controller;

import domain.Board;
import view.OutputView;

public class ChessController {
    private final OutputView outputView;

    public ChessController(OutputView outputView) {
        this.outputView = outputView;
    }

    public void printBoardStatus() {
        Board board = Board.initialize();
        outputView.printStatus(board.findCurrentStatus());
    }
}
