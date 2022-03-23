package chess.controller;

import chess.domain.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        while (requestContinue()) {
            startGame();
        }
    }

    private boolean requestContinue() {
        return InputView.inputContinueCommand();
    }

    private void startGame() {
        Board board = Board.createInitializedBoard();
        OutputView.printBoard(board);
    }
}
