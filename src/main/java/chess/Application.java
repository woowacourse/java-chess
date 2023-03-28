package chess;

import chess.controller.ErrorController;
import chess.controller.FrontController;
import chess.dao.DBRoomDao;
import chess.service.RoomConnectionService;

public final class Application {

    public static void main(String[] args) {
        FrontController frontController = new FrontController(new ErrorController(), new RoomConnectionService(new DBRoomDao()));
        frontController.run();
    }
}
