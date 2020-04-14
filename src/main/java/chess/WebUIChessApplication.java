package chess;

import static spark.Spark.*;

import chess.controller.WebUIChessController;
import chess.dao.BoardDao;
import chess.service.ChessService;

public class WebUIChessApplication {
    private static ChessService service = new ChessService(new BoardDao());
    private static WebUIChessController controller = new WebUIChessController(service);

    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/new", controller.getNewChessGameRoute());
        get("/", controller.getChessGameRoute());
        get("/result", controller.getResultRoute());
        get("/exception", controller.getExceptionRoute());
        post("/move", controller.getMoveRoute());
        post("/initialize", controller.getInitializeRoute());
    }
}
