package chess;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import chess.controller.GameController;

public class WebApplication {
    public static void main(String[] args) {
        port(8081);
        staticFileLocation("/static");
        new GameController().run();
    }
}
