package chess;

import chess.controller.WebChessController;
import spark.Spark;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Spark.staticFiles.location("/templates/login.html");
        WebChessController chessController = new WebChessController();
        chessController.run();
    }

}
