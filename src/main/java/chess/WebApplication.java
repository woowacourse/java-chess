package chess;

import static spark.Spark.staticFileLocation;

import chess.controller.ChessController;

public class WebApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");
        ChessController chessController = new ChessController();
        chessController.run();
    }
}
