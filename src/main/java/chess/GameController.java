package chess;

import chess.domain.board.Board;
import chess.view.OutputView;

public class GameController {
    private static final OutputView outputView = new OutputView();

    public void run() {
        createBoard();
    }

    private Board createBoard() {
        Board board = new Board();
        outputView.printBoard(board.getBoard());
        return board;
    }

}
