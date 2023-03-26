package chess;

import chess.controller.ErrorController;
import chess.controller.FrontController;
import chess.dao.DBBoardDao;
import chess.dao.DBChessGameDao;
import chess.domain.room.Rooms;

public final class Application {

    public static void main(String[] args) {
        FrontController frontController = new FrontController(new Rooms(new DBChessGameDao(new DBBoardDao())), new ErrorController());
        frontController.run();
    }
}
