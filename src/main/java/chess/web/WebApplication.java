package chess.web;

import static spark.Spark.staticFileLocation;

import chess.web.controller.ChessController;

public class WebApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");

        ChessController webChessController = new ChessController();
        webChessController.run();
    }
}
