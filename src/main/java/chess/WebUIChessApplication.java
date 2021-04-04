package chess;

import chess.controller.WebUIChessController;

import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");
        WebUIChessController webUIChessController = new WebUIChessController();
        webUIChessController.run();
    }
}
