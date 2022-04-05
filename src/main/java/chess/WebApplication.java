package chess;

import static spark.Spark.staticFileLocation;

import chess.controller.WebController;

public class WebApplication {
    public static void main(String[] args) {
        staticFileLocation("static");
        WebController webController = new WebController();
        webController.run();
    }
}
