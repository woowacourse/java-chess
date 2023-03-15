package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
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
        if (isStart()) {
            Board board = BoardFactory.createBoard();
            outputView.printBoard(board);
        }
    }

    private boolean isStart() {
        try {
            return inputView.readStartCommend();
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR]: " + e.getMessage());
            return isStart();
        }
    }
}
