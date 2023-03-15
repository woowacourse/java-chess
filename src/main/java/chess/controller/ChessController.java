package chess.controller;

import chess.domain.Board;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.HashMap;


public class ChessController {

    public void run() {
        try {
            InputView.askStart();

            final Board board = Board.create(new HashMap<>());
            OutputView.printBoard(board);

            playChessGame();
        } catch (final IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    private static void playChessGame() {
        String command = InputView.askNext();
        while (!command.equals("end")) {
            command = InputView.askNext();
            // .... 움직이는 로직
        }
    }
}
