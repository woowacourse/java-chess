package chess;

import chess.controller.GameController;
import chess.dao.InMemoryDao;

public class GameApplication {

    public static void main(String[] args) {
        GameController gameController = new GameController(new InMemoryDao());
        gameController.run();
    }
}
