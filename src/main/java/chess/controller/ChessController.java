package chess.controller;

import chess.domain.Board;
import chess.domain.Command;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printStartMessage();
        while (Command.of(InputView.requestCommand()).equals(Command.START)) {
            Board board = Board.create();
            OutputView.printBoard(board.getBoard());
        }
    }
}
