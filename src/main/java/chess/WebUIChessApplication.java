package chess;

import chess.controller.WebChessGameController;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");
        WebChessGameController webChessGameController = new WebChessGameController();
        webChessGameController.start();
    }
}
