package chess;

import chess.domain.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleController {

    public void run() {
        OutputView.printStartMessage();
        Board board = new Board();

        String input;
        do {
            input = InputView.requestStartOrEnd();
            printBoardIfStart(board, input);
        } while (!input.equals("end"));
    }

    private void printBoardIfStart(Board board, String input) {
        if (input.equals("start")) {
            OutputView.printBoard(board);
        }
    }
}
