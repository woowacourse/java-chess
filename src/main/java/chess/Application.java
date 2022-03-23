package chess;

import static chess.view.input.InputView.inputCommand;
import static chess.view.output.OutputView.printCurrentBoard;

import chess.domain.board.Board;
import chess.view.input.Command;

public class Application {

    public static void main(String[] args) {
        final Command command = inputCommand();
        Board board = new Board();
        printCurrentBoard(board);
    }
}
