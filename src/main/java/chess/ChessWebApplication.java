package chess;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import chess.controller.ChessWebController;

public class ChessWebApplication {

    public static void main(final String[] args) {
        port(8081);
        staticFileLocation("/static");

        new ChessWebController().run();
    }
}
