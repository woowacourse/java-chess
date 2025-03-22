package chess;

import chess.board.Board;
import chess.view.BoardView;

public class Game {
    private final BoardView boardView = new BoardView();

    public static void main(String[] args) {
        new Game().start();
    }

    public void start() {
        Board board = new Board();
        boardView.display(board);
    }
}
