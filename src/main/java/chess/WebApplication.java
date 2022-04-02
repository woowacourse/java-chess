package chess;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import chess.controller.WebChessController;

public class WebApplication {

    public static void main(String[] args) {
        staticFileLocation("/static");
        port(8080);

        WebChessController webChessController = new WebChessController();
        webChessController.run();
    }
}
