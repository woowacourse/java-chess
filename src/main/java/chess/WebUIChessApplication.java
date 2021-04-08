package chess;

import chess.controller.WebUIChessController;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");

        WebUIChessController webUIChessController = new WebUIChessController();
        webUIChessController.run();
    }
}
