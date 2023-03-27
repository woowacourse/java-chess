package chess;

import chess.controller.Controller;
import chess.dao.DbChessGameDao;

public class Application {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.playChessGame();
    }
}
