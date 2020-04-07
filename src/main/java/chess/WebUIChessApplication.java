package chess;

import chess.controller.ChessGameController;

import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");
//        get("/", ChessGameController::index);
        post("/new-game", ChessGameController::newGame);
        post("/continue-game", ChessGameController::continueGame);
        post("/move", ChessGameController::move);
    }
}
