package chess;

import chess.controller.WebController;
import chess.domain.ChessGameManager;

import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFileLocation("public");

        WebController webController = new WebController(new ChessGameManager());
        webController.run();
    }
}
