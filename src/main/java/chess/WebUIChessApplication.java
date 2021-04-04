package chess;

import chess.controller.ChessWebController;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) {
        ChessWebController chessWebController = new ChessWebController();
        chessWebController.route();
    }
}