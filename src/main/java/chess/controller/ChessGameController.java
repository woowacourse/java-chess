package chess.controller;

import chess.domain.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        if (inputView.inputStartCommand()) {
            Board board = new Board();
        }
    }
}
