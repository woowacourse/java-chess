package controller;

import static view.InputView.*;

import domain.ChessBoard;
import domain.ChessBoardGenerator;
import view.OutputView;

public class ChessController {

    public void play() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        String input = responseUserCommand();

        if (input.equals(START)){
            OutputView.printBoard(chessBoard);
        }

        if (input == END){
        }
    }
}
