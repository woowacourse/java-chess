package chess;

import chess.controller.MainController;
import chess.dao.ChessGameDao;

public class Application {
    public static void main(String[] args) {
        MainController mainController = new MainController(new ChessGameDao());
        mainController.run();
    }
}
