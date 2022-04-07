package chess;

import chess.controller.WebChessController;

import static spark.Spark.staticFiles;

public class WebApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");
        WebChessController webChessController = new WebChessController();

        webChessController.inputGameID();
        webChessController.preprocess();
        webChessController.backwardToPreprocess();
        webChessController.readyGame();
        webChessController.backwardToReady();
        webChessController.runTurn();
        webChessController.backwardToMove();
        webChessController.saveGame();
        webChessController.terminateGame();
    }
}
