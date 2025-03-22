package chess;

import chess.board.BoardManager;

public class Application {

    public static void main(String[] args) {
        BoardManager manager = new BoardManager();
        manager.move();
    }
}
