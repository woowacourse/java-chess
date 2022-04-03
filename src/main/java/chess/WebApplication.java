package chess;

import static spark.Spark.staticFileLocation;

import chess.controller.ChessWebController;

public class WebApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");
        ChessWebController controller = new ChessWebController();
        controller.run();
    }
}
