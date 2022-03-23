package chess;

import chess.domain.board.Board;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    public void init() {
        Board board;

        OutputView.printGuideMessage();
        while (!InputView.inputMenu().isEnd()) {
            board = createBoard();
            OutputView.printBoard(board.getBoard());
        }
    }

    private Board createBoard() {
        Board board = new Board();
        board.initBoard();
        return board;
    }
}
