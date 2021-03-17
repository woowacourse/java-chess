package chess.controller;

import chess.domain.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void run() {
        OutputView.printStartGuideMessage();

        while (InputView.inputStart()) {
            Board board = new Board();
            OutputView.printBoard(board);
        }
    }
}
