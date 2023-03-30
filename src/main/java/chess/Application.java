package chess;

import chess.controller.ChessController;
import chess.dao.ChessDao;
import chess.service.ChessService;

public class Application {
    public static void main(String[] args) {
        ChessService chessService = new ChessService(new ChessDao());
        ChessController chessController = new ChessController(chessService);
        chessController.run();
    }

}
