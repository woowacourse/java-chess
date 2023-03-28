package chess;

import chess.controller.ErrorController;
import chess.controller.FrontController;
import chess.dao.DBBoardDao;
import chess.dao.DBChessGameDao;
import chess.service.RoomService;

public final class Application {

    public static void main(String[] args) {
        FrontController frontController = new FrontController(new ErrorController(), new RoomService(new DBChessGameDao(new DBBoardDao())));
        frontController.run();
    }
}
