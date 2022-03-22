package controller;

import static view.InputView.END;
import static view.InputView.START;
import static view.InputView.responseUserCommand;

import domain.ChessBoard;
import domain.ChessBoardGenerator;
import view.OutputView;

public class ChessController {

    public void play() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        final String input = responseUserCommand();

        if (input.equals(START)) {
            OutputView.printBoard(chessBoard);
        }

        if (input.equals(END)) {
        }
    }
}
