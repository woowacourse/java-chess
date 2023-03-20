package chess;

import chess.controller.MainController;
import chess.domain.board.Board;

public class Application {
    public static void main(String[] args) {
        MainController mainController = new MainController(Board.create());
        mainController.run();
    }
}
