package chess;

import chess.controller.Controller;
import chess.dao.GameDao;
import chess.dao.MovementDao;

public class ChessGameApplication {
    public static void main(String[] args) {
        Controller controller = new Controller(new GameDao(), new MovementDao());
        controller.run();
    }
}
