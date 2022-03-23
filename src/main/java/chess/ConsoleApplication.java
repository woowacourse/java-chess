package chess;

import chess.view.OutputView;

public class ConsoleApplication {

    public static void main(final String[] args) {
        final Board board = Board.create();

        OutputView.printBoard(board);
    }
}
