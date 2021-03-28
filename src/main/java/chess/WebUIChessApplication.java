package chess;

import chess.controller.WebController;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");
        WebController webController = new WebController();
        webController.run();
    }
}
