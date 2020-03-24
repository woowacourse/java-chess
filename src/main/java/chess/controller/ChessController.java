package chess.controller;

import chess.domain.ChessBoard;
import chess.views.InputView;
import chess.views.OutputView;

public class ChessController {
    public static void main(String[] args) {
        OutputView.printInitialGuide();


        ChessBoard chessBoard;
        if(Commands.of(InputView.inputCommand()) == Commands.START){
            chessBoard = new ChessBoard();
        }

        if(Commands.of(InputView.inputCommand()) == Commands.END){
            return;
        }
    }
}
