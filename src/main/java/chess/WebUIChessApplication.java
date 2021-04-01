package chess;

import chess.controller.WebChessGameController;
import chess.dao.LogDAO;
import chess.dao.ResultDAO;
import chess.dao.RoomDAO;
import chess.dao.UserDAO;
import chess.domain.ChessGame;
import chess.service.LogService;
import chess.service.ResultService;
import chess.service.RoomService;
import chess.service.UserService;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");

        UserDAO userDAO = new UserDAO();
        WebChessGameController webChessGameController = new WebChessGameController(
                new RoomService(new RoomDAO()),
                new ResultService(new ResultDAO(), userDAO),
                new UserService(userDAO),
                new LogService(new LogDAO())
        );

        webChessGameController.start(new ChessGame());
    }
}
