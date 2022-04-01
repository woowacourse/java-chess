package chess.console;

import chess.console.board.Board;
import chess.console.board.BoardFactory;
import chess.console.view.OutputView;

public class ConsoleChessGame {
    private final Board board;

    public ConsoleChessGame() {
        this.board = BoardFactory.newInstance();
    }

    public void start() {
        OutputView.printBoard(board);
    }
}
