package chess;

import chess.controller.ChessController;
import chess.controller.WebChessController;
import spark.Spark;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Spark.staticFiles.location("/public");
        ChessController webChessController = new WebChessController();
        webChessController.run();
    }
}
