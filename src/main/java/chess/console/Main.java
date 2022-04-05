package chess.console;

import chess.console.controller.ChessController;
import chess.service.ChessService;
import chess.model.dao.RuntimeChessGameDao;

public class Main {

    public static void main(String[] args) {
        ChessController chessController = new ChessController(new ChessService(new RuntimeChessGameDao()));
        chessController.run();
    }
}
