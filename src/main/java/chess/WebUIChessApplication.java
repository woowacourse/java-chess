package chess;

import chess.controller.WebChessController;
import spark.Spark;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Spark.port(8080);
        Spark.staticFiles.location("/public");
        WebChessController webChessController = new WebChessController();
        webChessController.run();
    }
}
