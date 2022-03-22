package chess.controller;

import chess.Board;
import chess.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void run() {
        InputView.startGame();
        Command command = new Command(InputView.inputCommand());
        Board board = Board.create();

        while (!command.isEnd()) {
            OutputView.printBoard(board.getBoard());
            command = new Command(InputView.inputCommand());
        }
    }
}
