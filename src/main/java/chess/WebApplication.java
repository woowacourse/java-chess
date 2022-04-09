package chess;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

import chess.controller.ChessController;

public class WebApplication {

    public static void main(String[] args) {
        staticFiles.location("/static");
        port(8080);

        ChessController chessController = new ChessController();
        chessController.run();
        chessController.start();
        chessController.runMain();
        chessController.board();
        chessController.turn();
        chessController.move();
        chessController.status();
        chessController.end();
    }

}
