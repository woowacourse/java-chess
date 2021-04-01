package chess;

import chess.controller.ChessController;
import spark.Spark;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Spark.staticFileLocation("/static");
        new ChessController().run();
    }
}
