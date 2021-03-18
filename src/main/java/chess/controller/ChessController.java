package chess.controller;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printManual();
        String command = "";
        Board board = null;

        while (true) {
            command = InputView.inputCommand();
            if (command.equals("end")) {
                break;
            }
            if (command.equals("start")) {
                board = BoardFactory.create();
            }
            if (command.equals("move")) {

            }
            OutputView.printBoard(board);
        }
    }

    private boolean isContinue(String command) {
        return "start".equals(command);
    }
}