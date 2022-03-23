package chess;

import chess.domain.Board;
import chess.domain.BoardInitiator;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {

    public static void main(String[] args) {
        InputView.announceStart();
        while (true) {
            String command = InputView.requestCommand();
            if (command.trim().equals("end")) {
                return;
            }
            if (command.trim().equals("start")) {
                Board board = new Board(new BoardInitiator());
                OutputView.printBoard(board);
            }
        }
    }
}
