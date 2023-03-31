package chess;

import chess.controller.ChessGameController;
import chess.service.GameService;

public class Application {

    public static void main(String[] args) {
        new ChessGameController(new GameService()).run();
    }

}
