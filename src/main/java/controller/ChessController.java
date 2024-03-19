package controller;

import domain.ChessTable;
import view.InputView;
import view.OutputView;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        final ChessTable chessTable = ChessTable.create();
        outputView.printChessTable(chessTable.getPieceContainer());
    }


}
