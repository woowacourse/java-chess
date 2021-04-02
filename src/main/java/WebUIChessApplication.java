import static spark.Spark.port;
import static spark.Spark.staticFiles;

import chess.controller.WebController;
import chess.repository.GameDao;
import chess.repository.GameHistoryDao;
import chess.repository.UserDao;
import chess.service.ChessService;
import chess.service.UserService;

public class WebUIChessApplication {
    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/public");
        ChessService chessService = new ChessService(new GameDao(), new GameHistoryDao());
        UserService userService = new UserService(new UserDao());
        WebController webController = new WebController(chessService, userService);
        webController.run();
    }
}
