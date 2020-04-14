package chess;

import static spark.Spark.*;

import chess.controller.WebUIChessController;

public class WebUIChessApplication {
    private static WebUIChessController controller = new WebUIChessController();

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
