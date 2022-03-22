package chess;

import chess.domain.Board;
import chess.view.OutputView;

public class ConsoleApplication {

    public static void main(String[] args) {
        Board board = new Board();
        OutputView.printBoard(board);
    }
}
