package chess;

import chess.controller.ChessController;
import spark.Spark;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Spark.staticFileLocation("/static");
        new ChessController().run();
    }
}
