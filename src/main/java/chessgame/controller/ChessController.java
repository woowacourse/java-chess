package chessgame.controller;

import chessgame.domain.Board;
import chessgame.util.ChessBoardFactory;
import chessgame.view.InputView;
import chessgame.view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Board board = new Board(ChessBoardFactory.create());
        outputView.printChessBoard(board);
    }
}
