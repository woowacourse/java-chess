package chess.controller;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private final OutputView outputView;
    private final InputView inputView;

    public ChessController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        Board board = BoardFactory.createBoard();
        outputView.printBoard(board);
    }
}
