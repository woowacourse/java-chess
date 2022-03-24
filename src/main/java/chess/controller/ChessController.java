package chess.controller;

import chess.Board;
import chess.command.Command;
import chess.command.Init;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void run() {
        InputView.startGame();

        String input = InputView.inputCommand();
        Command command = new Init(input);
        Board board = Board.create();
        command = command.turnState(input);
        while (!command.isEnd()) {
            OutputView.printBoard(board.getBoard());
            command = command.turnState(InputView.inputCommand());
        }
    }
}
