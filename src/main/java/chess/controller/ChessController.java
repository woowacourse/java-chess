package chess.controller;

import chess.domain.Command;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        OutputView.printManual();
        Board board = null;

        while (true) {
            Command.execute(InputView.inputCommand(), this);
            OutputView.printBoard(board);
        }
    }

    public void start() {

    }

    public void end() {

    }

    public void move(String s1, String s2) {

    }
}