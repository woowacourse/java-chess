package chess;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import chess.domain.controller.ChessController;

public class WebApplication {

    public static void main(final String[] args) {
        port(8081);
        staticFileLocation("/static");

        new ChessController().run();
    }
}
