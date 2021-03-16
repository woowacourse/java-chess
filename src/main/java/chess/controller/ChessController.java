package chess.controller;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        Board board = BoardFactory.initializeBoard();
        OutputView.printBoard(board);
    }
}
