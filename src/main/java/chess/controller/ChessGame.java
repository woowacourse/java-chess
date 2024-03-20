package chess.controller;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {

    public void run() {
        String command = InputView.readCommand();
        if (command.equals("start")) {
            Board board = BoardFactory.createBoard();
            OutputView.printInitialBoard(board.get());
        }
    }
}
