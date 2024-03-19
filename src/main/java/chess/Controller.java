package chess;

import chess.domain.Board;
import chess.view.InputView;
import chess.view.OutputView;

class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        Board board = new Board();
        if (inputView.readStartCommand()) {
            play(board);
        }
    }

    private void play(Board board) {
        outputView.printBoard(board);
    }
}
