package chess;

import chess.controller.web.WebChessController;
import chess.dao.CommandDao;
import chess.dao.RoomDao;
import chess.service.ChessService;
import chess.service.RoomService;

public class WebUIChessApplication {
    public static void main(String[] args) {
        CommandDao commandDao = new CommandDao();
        RoomDao roomDao = new RoomDao();

        WebChessController chessController =
                new WebChessController(new ChessService(commandDao), new RoomService(roomDao));
        chessController.run();
    }
}
