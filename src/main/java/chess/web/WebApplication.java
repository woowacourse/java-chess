package chess.web;

import static spark.Spark.staticFileLocation;

import chess.web.controller.WebController;

public class WebApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");

        final WebController webController = new WebController();
        webController.run();

    }
}
