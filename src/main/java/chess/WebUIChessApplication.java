package chess;

import chess.controller.ChessWebController;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");
        ChessWebController chessWebController = new ChessWebController();
        chessWebController.route();
    }
}