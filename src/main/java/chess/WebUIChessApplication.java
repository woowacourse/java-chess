package chess;

import chess.web.ChessController;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {

    public static void main(String[] args) {
        staticFiles.location("/templates");

        ChessController chessController = new ChessController();
        chessController.run();
    }

}
