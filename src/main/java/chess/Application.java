package chess;

import chess.web.ChessController;

import static spark.Spark.staticFiles;

public class Application {

    public static void main(String[] args) {
        ChessController chessController = new ChessController();
        chessController.run();
    }

}
