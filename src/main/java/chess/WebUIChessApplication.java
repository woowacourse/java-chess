package chess;

import chess.controller.WebChessController;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {
    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/static");
        WebChessController webChessController = new WebChessController();
        webChessController.route();
    }
}
