package chess;

import chess.controller.WebChessController;

import static spark.Spark.*;

public class WebApplication {
    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/static");

        WebChessController webChessController = new WebChessController();
        webChessController.run();
    }
}
