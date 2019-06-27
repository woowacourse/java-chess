package chess;

import chess.controller.ChessGameController;
import chess.service.ChessService;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static final int PORT_NO = 8080;

    public static void main(String[] args) {
        ChessService service = new ChessService();
        ChessGameController gameController = new ChessGameController(service);

        port(PORT_NO);

        staticFiles.location("/templates");

        get("/", gameController.serveIndexPage);
        get("/initialize", gameController.initialize);
        get("/loadBoard", gameController.loadBoard);

        post("/move", gameController.move);
    }
}
