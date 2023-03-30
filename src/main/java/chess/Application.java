package chess;

import chess.controller.ChessGameController;
import chess.domain.dao.DBInitFactory;

public class Application {

    public static void main(String[] args) {
        DBInitFactory.initDB();
        new ChessGameController().run();
    }

}
