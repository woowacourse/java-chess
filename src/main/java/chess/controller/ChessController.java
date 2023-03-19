package chess.controller;

import chess.domain.Board;
import chess.domain.command.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.HashMap;


public class ChessController {

    public void run() {
        Board board = Board.create(new HashMap<>());

        startGame(board);
        playGame(board);
    }

    private static void startGame(final Board board) {
        Command.ofStart(InputView.askStart());
        OutputView.printBoard(board);
    }

    private static void playGame(final Board board) {
        Command command = Command.ofMoveOrEnd(InputView.askNext());
        if (command.isEnd()) {
            return;
        }
        if (command.isMove()) {
            board.move(command.getSource(), command.getTarget());
            OutputView.printBoard(board);
            playGame(board);
        }
    }
}
