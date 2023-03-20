package chess;

import chess.controller.MainController;
import chess.domain.board.Board;
import chess.domain.board.BoardMaker;

public class Application {
    public static void main(String[] args) {
        MainController mainController = new MainController(
                new Board(new BoardMaker())
        );
        mainController.run();
    }
}
