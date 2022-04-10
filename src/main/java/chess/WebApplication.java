package chess;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

import chess.controller.web.WebGameController;

public class WebApplication {
    public static void main(String[] args) {
        port(8080);
        staticFiles.location("/static");
        WebGameController webGameController = new WebGameController();
        webGameController.run();
    }
}
