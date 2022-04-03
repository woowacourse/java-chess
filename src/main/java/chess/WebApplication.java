package chess;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import chess.controller.WebChessController;

public class WebApplication {

    public static void main(String[] args) {
        port(8081);
        staticFileLocation("/static");
        WebChessController webChessController = new WebChessController();
        webChessController.run();
    }
}
