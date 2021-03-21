package controller;

import domain.Board;
import domain.PieceFactory;
import view.OutputView;

public class ChessController {
    public void run() {
        Board board = new Board(PieceFactory.createPieces());
        OutputView.showBoard(board);
    }
}
