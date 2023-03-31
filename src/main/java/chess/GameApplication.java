package chess;

import chess.controller.GameController;
import chess.dao.DbChessGameDao;

public class GameApplication {

    public static void main(String[] args) {
        GameController gameController = new GameController(new DbChessGameDao());
        gameController.run();
    }
}
