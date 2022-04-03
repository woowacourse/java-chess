package chess;

import chess.controller.WebChessGameController;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class WebApplication {
    public static void main(String[] args) {
        port(8081);
        staticFileLocation("/public");
        new WebChessGameController().run();
    }
}
