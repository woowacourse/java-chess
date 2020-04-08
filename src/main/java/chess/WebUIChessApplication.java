package chess;

import chess.controller.ChessWebController;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/public");

        ChessWebController controller = new ChessWebController();
        controller.play();
    }
}
