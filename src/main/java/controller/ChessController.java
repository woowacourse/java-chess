package controller;

import domain.chess.Board;
import domain.chess.PieceFactory;
import view.OutputView;

public class ChessController {

    public void run() {
        Board board = new Board(PieceFactory.createPieces());
        OutputView.showBoard(board);
    }
}
