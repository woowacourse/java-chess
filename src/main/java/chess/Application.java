package chess;

import chess.domain.ChessGame;
import chess.domain.controller.MainController;

public class Application {
    public static void main(String[] args) {
        MainController mainController = new MainController(new ChessGame());
        mainController.run();
    }
}
