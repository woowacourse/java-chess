package chess;

import chess.controller.ChessController;

import static spark.Spark.port;

public class WebApplication {

    public static void main(String[] args) {
        port(8080);

        new ChessController().run();
    }
}