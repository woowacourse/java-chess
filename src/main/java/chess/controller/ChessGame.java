package chess.controller;

import chess.domain.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    public void start() {
        OutputView.startGame();
        String command = InputView.command();
        if (command.equals("start")) {
            Board board = new Board();
            OutputView.printBoard(board);
        }
    }
}
