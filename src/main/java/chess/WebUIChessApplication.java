package chess;

import chess.controller.ChessController;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");
        ChessController.getInstance().init();
    }
}
