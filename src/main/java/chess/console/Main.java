package chess.console;

import chess.console.controller.ChessController;
import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.service.ChessService;

public class Main {

    public static void main(String[] args) {
        ChessController chessController = new ChessController(new ChessService(new BoardDao(), new GameDao()));
        chessController.run();
    }
}
