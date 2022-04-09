package chess;

import static spark.Spark.*;

import chess.controller.WebController;

public class WebApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");
        port(8080);

        WebController webController = new WebController();

        get("/", webController.ready());
        get("/findgame", webController.askGameID());
        post("/findgame", webController.findGame());
        get("/ingame", webController.runGame());
        post("/ingame", webController.movePiece());
        get("/status", webController.showResult());
    }
}
