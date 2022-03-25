package chess;

import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        Board board = new Board();
        OutputView outputView = new OutputView();
        outputView.printBoard(board.getBoard());
    }
}
