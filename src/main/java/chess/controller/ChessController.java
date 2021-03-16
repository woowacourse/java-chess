package chess.controller;

import chess.domain.Board;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        Board board = new Board();
        OutputView.printBoard(board);
    }
}
