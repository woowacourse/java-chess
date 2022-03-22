package controller;

import domain.ChessBoard;
import domain.ChessBoardGenerator;
import view.Output;

public class ChessController {

    public void play() {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        Output.printBoard(chessBoard);
    }
}
