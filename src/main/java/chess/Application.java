package chess;

import chess.controller.ChessController;

import static spark.Spark.staticFiles;

public class Application {

    public static void main(String[] args) {
        staticFiles.location("/templates");

        ChessController chessController = new ChessController();
        chessController.run();
    }

}
